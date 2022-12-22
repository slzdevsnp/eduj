package io.axoniq.training.labs.giftcard.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class ReimburseCardCommand {

    @TargetAggregateIdentifier
    private final String cardId;
    private final String transactionId;

    public ReimburseCardCommand(String cardId, String transactionId) {
        this.cardId = cardId;
        this.transactionId = transactionId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReimburseCardCommand that = (ReimburseCardCommand) o;
        return Objects.equals(cardId, that.cardId) &&
                Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, transactionId);
    }

    @Override
    public String toString() {
        return "ReimburseCardCommand{" +
                "cardId='" + cardId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
