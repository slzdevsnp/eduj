package io.axoniq.training.labs.booking.coreapi;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class ConfirmBookingCommand {

    @RoutingKey
    private final String bookingId;
    private final String cardId;
    private final int giftCardAmount;


    public ConfirmBookingCommand(String bookingId, String cardId, int giftCardAmount) {
        this.bookingId = bookingId;
        this.cardId = cardId;
        this.giftCardAmount = giftCardAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCardId() {
        return cardId;
    }

    public int getGiftCardAmount() {
        return giftCardAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfirmBookingCommand that = (ConfirmBookingCommand) o;
        return giftCardAmount == that.giftCardAmount &&
                Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, cardId, giftCardAmount);
    }

    @Override
    public String toString() {
        return "ConfirmBookingCommand{" +
                "bookingId='" + bookingId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", giftCardAmount=" + giftCardAmount +
                '}';
    }
}
