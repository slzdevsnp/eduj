package io.axoniq.training.labs.giftcard;

import io.axoniq.training.labs.booking.BookingCommandHandler;
import org.axonframework.axonserver.connector.TargetContextResolver;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.Configuration;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.TrackedEventMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.MultiStreamableMessageSource;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.messaging.interceptors.LoggingInterceptor;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HotelGiftCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelGiftCardApplication.class, args);
    }

    @Bean
    @Profile("booking")
    public BookingCommandHandler bookingCommandHandler(EventGateway eventGateway) {
        return new BookingCommandHandler(eventGateway);
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DeadlineManager deadlineManager(Configuration configuration) {
        return SimpleDeadlineManager.builder()
                                    .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                                    .build();
    }

    @Bean(name = "giftCardSnapshotTriggerDefinition")
    public SnapshotTriggerDefinition giftCardSnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 10);
    }

    @Bean
    public LoggingInterceptor<Message<?>> loggingInterceptor() {
        return new LoggingInterceptor<>();
    }

    @Autowired
    public void configureLoggingInterceptorFor(CommandBus commandBus,
                                               LoggingInterceptor<Message<?>> loggingInterceptor) {
        commandBus.registerDispatchInterceptor(loggingInterceptor);
        commandBus.registerHandlerInterceptor(loggingInterceptor);
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public void configureLoggingInterceptorFor(EventBus eventBus, LoggingInterceptor<Message<?>> loggingInterceptor) {
        eventBus.registerDispatchInterceptor(loggingInterceptor);
    }

    @Autowired
    public void configureLoggingInterceptorFor(EventProcessingConfigurer eventProcessingConfigurer,
                                               LoggingInterceptor<Message<?>> loggingInterceptor) {
        eventProcessingConfigurer.registerDefaultHandlerInterceptor((config, processorName) -> loggingInterceptor);
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public void configureLoggingInterceptorFor(QueryBus queryBus, LoggingInterceptor<Message<?>> loggingInterceptor) {
        queryBus.registerDispatchInterceptor(loggingInterceptor);
        queryBus.registerHandlerInterceptor(loggingInterceptor);
    }

    @Bean
    @Qualifier("eventSerializer")
    public Serializer eventSerializer() {
        return JacksonSerializer.defaultSerializer();
    }

    @Bean
    @Profile("saga")
    public ConfigurerModule configureSagaProcessor() {
        return configurer -> configurer.eventProcessing()
                                       .registerTrackingEventProcessor("BookingSagaProcessor",
                                                                       c -> buildMultiContextStreamableSource(
                                                                               (AxonServerEventStore) c.eventStore()
                                                                       ));
    }

    private MultiStreamableMessageSource buildMultiContextStreamableSource(AxonServerEventStore eventStore) {
        StreamableMessageSource<TrackedEventMessage<?>> bookingSource =
                eventStore.createStreamableMessageSourceForContext("booking");
        StreamableMessageSource<TrackedEventMessage<?>> giftcardSource =
                eventStore.createStreamableMessageSourceForContext("giftcard");
        return MultiStreamableMessageSource.builder()
                                           .addMessageSource("booking", bookingSource)
                                           .addMessageSource("giftcard", giftcardSource)
                                           .build();
    }

    @Bean
    public TargetContextResolver<?> targetContextResolver() {
        return message -> message.getPayloadType().getName().contains(".booking.") ? "booking" : "giftcard";
    }
}
