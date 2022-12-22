package io.axoniq.training.labs.giftcard.coreapi;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class IssueCardCommand {

    @RoutingKey
    private final String cardId;
    private final int amount;
    private final String shopId;

    public IssueCardCommand(String cardId, int amount, String shopId) {
        this.cardId = cardId;
        this.amount = amount;
        this.shopId = shopId;
    }

    public String getCardId() {
        return cardId;
    }

    public int getAmount() {
        return amount;
    }

    public String getShopId() {
        return shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IssueCardCommand that = (IssueCardCommand) o;
        return amount == that.amount &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, amount, shopId);
    }

    @Override
    public String toString() {
        return "IssueCardCommand{" +
                "cardId='" + cardId + '\'' +
                ", amount=" + amount +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
