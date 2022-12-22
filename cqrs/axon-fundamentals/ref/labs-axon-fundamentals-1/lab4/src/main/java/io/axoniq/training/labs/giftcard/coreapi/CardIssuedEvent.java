package io.axoniq.training.labs.giftcard.coreapi;

import java.util.Objects;

public class CardIssuedEvent {

    private final String cardId;
    private final int amount;

    public CardIssuedEvent(String cardId, int amount) {
        this.cardId = cardId;
        this.amount = amount;
    }

    public String getCardId() {
        return cardId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardIssuedEvent that = (CardIssuedEvent) o;
        return amount == that.amount &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, amount);
    }

    @Override
    public String toString() {
        return "CardIssuedEvent{" +
                "cardId='" + cardId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
