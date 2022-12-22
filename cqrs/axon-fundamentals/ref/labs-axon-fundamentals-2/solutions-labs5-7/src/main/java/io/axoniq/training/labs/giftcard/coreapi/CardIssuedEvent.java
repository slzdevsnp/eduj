package io.axoniq.training.labs.giftcard.coreapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.serialization.Revision;

import java.util.Objects;

@Revision("1")
public class CardIssuedEvent {

    private final String cardId;
    private final int amount;
    private final String shopId;

    public CardIssuedEvent(@JsonProperty("cardId") String cardId,
                           @JsonProperty("amount") int amount,
                           @JsonProperty("shopId") String shopId) {
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
        CardIssuedEvent that = (CardIssuedEvent) o;
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
        return "CardIssuedEvent{" +
                "cardId='" + cardId + '\'' +
                ", amount=" + amount +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
