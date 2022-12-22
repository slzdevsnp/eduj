package io.axoniq.training.labs.giftcard.coreapi;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class IssueCardCommand {

    @RoutingKey
    private final String cardId;
    private final int amount;

    public IssueCardCommand(String cardId, int amount) {
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
        IssueCardCommand giftCard = (IssueCardCommand) o;
        return amount == giftCard.amount &&
                cardId.equals(giftCard.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, amount);
    }

    @Override
    public String toString() {
        return "IssueCardCommand{" +
                "cardId='" + cardId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
