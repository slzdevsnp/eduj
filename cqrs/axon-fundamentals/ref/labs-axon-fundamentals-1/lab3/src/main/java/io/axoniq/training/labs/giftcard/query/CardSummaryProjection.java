package io.axoniq.training.labs.giftcard.query;

import io.axoniq.training.labs.giftcard.coreapi.CardSummary;
import io.axoniq.training.labs.giftcard.coreapi.CountCardSummariesQuery;
import io.axoniq.training.labs.giftcard.coreapi.FindCardSummariesQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CardSummaryProjection {

    private final CardSummaryRepository cardSummaryRepository;

    public CardSummaryProjection(CardSummaryRepository cardSummaryRepository) {
        this.cardSummaryRepository = cardSummaryRepository;
    }

    @QueryHandler
    public List<CardSummary> handle(FindCardSummariesQuery query) {
        return Collections.emptyList();
    }

    @QueryHandler
    public Long handle(CountCardSummariesQuery query) {
        return 0L;
    }
}
