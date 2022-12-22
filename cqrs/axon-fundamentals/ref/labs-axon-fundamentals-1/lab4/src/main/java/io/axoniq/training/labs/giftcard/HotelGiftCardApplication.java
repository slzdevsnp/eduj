package io.axoniq.training.labs.giftcard;

import io.axoniq.training.labs.booking.BookingCommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelGiftCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelGiftCardApplication.class, args);
    }

    @Bean
    public BookingCommandHandler orderCommandHandler() {
        return new BookingCommandHandler();
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DeadlineManager deadlineManager(Configuration configuration) {
        return SimpleDeadlineManager.builder()
                                    .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                                    .build();
    }
}
