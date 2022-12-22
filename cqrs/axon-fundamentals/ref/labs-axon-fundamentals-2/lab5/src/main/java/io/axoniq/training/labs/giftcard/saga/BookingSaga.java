package io.axoniq.training.labs.giftcard.saga;

import io.axoniq.training.labs.booking.coreapi.BookingPlacedEvent;
import io.axoniq.training.labs.booking.coreapi.ConfirmBookingCommand;
import io.axoniq.training.labs.booking.coreapi.RejectBookingCommand;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import io.axoniq.training.labs.giftcard.coreapi.RedeemCardCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static io.axoniq.training.labs.booking.coreapi.RejectBookingCommand.INSUFFICIENT_FUNDS;
import static io.axoniq.training.labs.booking.coreapi.RejectBookingCommand.PARTIAL_PAYMENT_DEADLINE_EXPIRED;

@Saga
public class BookingSaga {

    private transient CommandGateway commandGateway;
    private transient DeadlineManager deadlineManager;

    private String bookingId;
    private String cardId;
    private boolean partialPayment;

    @StartSaga
    @SagaEventHandler(associationProperty = "bookingId")
    public void on(BookingPlacedEvent event) {
        this.bookingId = event.getBookingId();
        this.cardId = event.getCardId();
        this.partialPayment = event.isPartialPayment();

        SagaLifecycle.associateWith("cardId", cardId);

        if (partialPayment) {
            deadlineManager.schedule(Duration.of(7, ChronoUnit.DAYS), "paymentDeadline");
        }

        commandGateway.send(new RedeemCardCommand(cardId, bookingId, event.getGiftCardAmount()))
                      .exceptionally(throwable -> {
                          commandGateway.send(new RejectBookingCommand(bookingId, cardId, INSUFFICIENT_FUNDS));
                          SagaLifecycle.end();
                          return null;
                      });
    }

    @SagaEventHandler(associationProperty = "cardId")
    public void on(CardRedeemedEvent event) {
        if (!bookingId.equals(event.getTransactionId())) {
            return;
        }

        commandGateway.send(new ConfirmBookingCommand(bookingId, event.getCardId(), event.getAmount()));
        if (!partialPayment) {
            SagaLifecycle.end();
        }
    }

    @DeadlineHandler(deadlineName = "paymentDeadline")
    public void handle() {
        commandGateway.send(new RejectBookingCommand(bookingId, cardId, PARTIAL_PAYMENT_DEADLINE_EXPIRED));
        SagaLifecycle.end();
    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Autowired
    public void setDeadlineManager(DeadlineManager deadlineManager) {
        this.deadlineManager = deadlineManager;
    }
}