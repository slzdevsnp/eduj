package io.axoniq.training.labs.booking.coreapi;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class PlaceBookingCommand {

    @RoutingKey
    private final String bookingId;
    private final String cardId;
    private final int giftCardAmount;
    private final boolean partialPayment;

    public PlaceBookingCommand(String bookingId, String cardId, int giftCardAmount, boolean partialPayment) {
        this.bookingId = bookingId;
        this.cardId = cardId;
        this.giftCardAmount = giftCardAmount;
        this.partialPayment = partialPayment;
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

    public boolean isPartialPayment() {
        return partialPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlaceBookingCommand that = (PlaceBookingCommand) o;
        return giftCardAmount == that.giftCardAmount &&
                partialPayment == that.partialPayment &&
                Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, cardId, giftCardAmount, partialPayment);
    }
}
