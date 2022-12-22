package io.axoniq.training.labs.booking.coreapi;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class RejectBookingCommand {

    public static final String INSUFFICIENT_FUNDS = "Insufficient funds available on referenced gift card.";
    public static final String PARTIAL_PAYMENT_DEADLINE_EXPIRED = "A partial payment was requested, but the deadline has been expired.";

    @RoutingKey
    private final String bookingId;
    private final String cardId;
    private final String reason;

    public RejectBookingCommand(String bookingId, String cardId, String reason) {
        this.bookingId = bookingId;
        this.cardId = cardId;
        this.reason = reason;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RejectBookingCommand that = (RejectBookingCommand) o;
        return Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, cardId, reason);
    }

    @Override
    public String toString() {
        return "RejectBookingCommand{" +
                "bookingId='" + bookingId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
