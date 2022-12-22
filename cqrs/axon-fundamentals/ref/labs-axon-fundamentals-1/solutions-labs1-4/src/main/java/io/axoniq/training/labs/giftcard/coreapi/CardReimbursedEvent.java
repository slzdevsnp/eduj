package io.axoniq.training.labs.giftcard.coreapi;

import java.util.Objects;

public class CardReimbursedEvent {

    private final String cardId;
    private final String transactionId;
    private final int amount;

    public CardReimbursedEvent(String cardId, String transactionId, int amount) {
        this.cardId = cardId;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public String getCardId() {
        return cardId;
    }

    public String getTransactionId() {
        return transactionId;
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
        CardReimbursedEvent that = (CardReimbursedEvent) o;
        return amount == that.amount &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, transactionId, amount);
    }

    @Override
    public String toString() {
        return "CardReimbursedEvent{" +
                "cardId='" + cardId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
