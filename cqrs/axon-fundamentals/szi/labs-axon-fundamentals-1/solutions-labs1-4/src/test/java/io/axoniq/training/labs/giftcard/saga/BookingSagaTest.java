package io.axoniq.training.labs.giftcard.saga;

import io.axoniq.training.labs.booking.coreapi.BookingPlacedEvent;
import io.axoniq.training.labs.booking.coreapi.ConfirmBookingCommand;
import io.axoniq.training.labs.booking.coreapi.RejectBookingCommand;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import org.axonframework.deadline.DeadlineMessage;
import org.axonframework.test.matchers.Matchers;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static io.axoniq.training.labs.booking.coreapi.RejectBookingCommand.PARTIAL_PAYMENT_DEADLINE_EXPIRED;

class BookingSagaTest {

    private static final String BOOKING_ID = "bookingId";
    private static final String CARD_ID = "cardId";
    private static final boolean NO_PARTIAL_PAYMENT = false;
    private static final boolean PARTIAL_PAYMENT = true;

    private SagaTestFixture<BookingSaga> fixture;

    @BeforeEach
    void setUp() {
        this.fixture = new SagaTestFixture<>(BookingSaga.class);
    }

    @Test
    void shouldRedeemFromGiftCardWhenBookingIsPlaced() {
        fixture.givenAPublished(new BookingPlacedEvent(BOOKING_ID, CARD_ID, 100, NO_PARTIAL_PAYMENT))
               .whenPublishingA(new CardRedeemedEvent(CARD_ID, BOOKING_ID, 100))
               .expectDispatchedCommands(new ConfirmBookingCommand(BOOKING_ID, CARD_ID, 100))
               .expectActiveSagas(0);
    }

    @Test
    void shouldRejectBookingOnPartialPaymentDeadlineExpired() {
        fixture.givenAPublished(new BookingPlacedEvent(BOOKING_ID, CARD_ID, 100, PARTIAL_PAYMENT))
               .whenTimeElapses(Duration.of(7, ChronoUnit.DAYS))
               .expectDeadlinesMetMatching(Matchers.matches(
                       deadlines -> deadlines.size() == 1
                               && "paymentDeadline".equals(((DeadlineMessage<?>) deadlines.get(0)).getDeadlineName())
               ))
               .expectDispatchedCommands(
                       new RejectBookingCommand(BOOKING_ID, CARD_ID, PARTIAL_PAYMENT_DEADLINE_EXPIRED)
               )
               .expectActiveSagas(0);
    }
}
