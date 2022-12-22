package io.axoniq.training.labs.booking.coreapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookingPlacedEvent {

    private final String bookingId;
    private final String cardId;
    private final int giftCardAmount;
    private final boolean partialPayment;

    public BookingPlacedEvent(@JsonProperty("bookingId") String bookingId,
                              @JsonProperty("cardId") String cardId,
                              @JsonProperty("giftCardAmount") int giftCardAmount,
                              @JsonProperty("partialPayment") boolean partialPayment) {
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
        BookingPlacedEvent that = (BookingPlacedEvent) o;
        return giftCardAmount == that.giftCardAmount &&
                partialPayment == that.partialPayment &&
                Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, cardId, giftCardAmount, partialPayment);
    }

    @Override
    public String toString() {
        return "BookingPlacedEvent{" +
                "bookingId='" + bookingId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", giftCardAmount=" + giftCardAmount +
                ", partialPayment=" + partialPayment +
                '}';
    }
}
