package io.axoniq.training.labs.giftcard.query;

import io.axoniq.training.labs.giftcard.coreapi.CardSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSummaryRepository extends JpaRepository<CardSummary, String> {

}
