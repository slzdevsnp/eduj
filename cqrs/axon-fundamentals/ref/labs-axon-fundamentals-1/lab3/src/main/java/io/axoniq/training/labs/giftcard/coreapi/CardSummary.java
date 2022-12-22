package io.axoniq.training.labs.giftcard.coreapi;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardSummary {

    @Id
    private String cardId = "";

    public String getCardId() {
        return null;
    }

    public int getInitialValue() {
        return 0;
    }

    public Instant getIssuedAt() {
        return Instant.now();
    }

    public int getRemainingValue() {
        return 0;
    }
}
