package io.axoniq.training.labs.giftcard.coreapi;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardSummary {

    @Id
    private String cardId;
    private Integer initialValue;
    private Instant issuedAt;
    private Integer remainingValue;
    private Integer numberOfTransactions;
    private String issuedBy;

    public CardSummary(String cardId, int initialValue, Instant issuedAt, String issuedBy) {
        this.cardId = cardId;
        this.initialValue = initialValue;
        this.issuedAt = issuedAt;
        this.remainingValue = initialValue;
        this.numberOfTransactions = 0;
        this.issuedBy = issuedBy;
    }

    public CardSummary() {
    }

    public String getCardId() {
        return this.cardId;
    }

    public Integer getInitialValue() {
        return this.initialValue;
    }

    public Instant getIssuedAt() {
        return this.issuedAt;
    }

    public Integer getRemainingValue() {
        return this.remainingValue;
    }

    public void setRemainingValue(int remainingValue) {
        this.remainingValue = remainingValue;
    }

    public Integer getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Integer numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getIssuedBy() {
        return this.issuedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardSummary that = (CardSummary) o;
        return Objects.equals(cardId, that.cardId) &&
                Objects.equals(initialValue, that.initialValue) &&
                Objects.equals(issuedAt, that.issuedAt) &&
                Objects.equals(remainingValue, that.remainingValue) &&
                Objects.equals(numberOfTransactions, that.numberOfTransactions) &&
                Objects.equals(issuedBy, that.issuedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, initialValue, issuedAt, remainingValue, numberOfTransactions, issuedBy);
    }

    @Override
    public String toString() {
        return "CardSummary{" +
                "cardId='" + cardId + '\'' +
                ", initialValue=" + initialValue +
                ", issuedAt=" + issuedAt +
                ", remainingValue=" + remainingValue +
                ", numberOfTransactions=" + numberOfTransactions +
                ", issuedBy='" + issuedBy + '\'' +
                '}';
    }
}
