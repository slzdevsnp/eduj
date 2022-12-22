package io.axoniq.training.labs.giftcard.query;

import io.axoniq.training.labs.giftcard.coreapi.CardIssuedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardReimbursedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardSummary;
import io.axoniq.training.labs.giftcard.coreapi.CountCardSummariesQuery;
import io.axoniq.training.labs.giftcard.coreapi.FindCardSummariesQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class CardSummaryProjection {

    private final CardSummaryRepository cardSummaryRepository;

    public CardSummaryProjection(CardSummaryRepository cardSummaryRepository) {
        this.cardSummaryRepository = cardSummaryRepository;
    }

    @EventHandler
    public void on(CardIssuedEvent event, @Timestamp Instant issuedAt) {
        cardSummaryRepository.save(new CardSummary(event.getCardId(), event.getAmount(), issuedAt));
    }

    @EventHandler
    public void on(CardRedeemedEvent event) {
        cardSummaryRepository.findById(event.getCardId()).ifPresent(
                cardSummary -> cardSummary.setRemainingValue(cardSummary.getRemainingValue() - event.getAmount())
        );
    }

    @EventHandler
    public void on(CardReimbursedEvent event) {
        cardSummaryRepository.findById(event.getCardId()).ifPresent(
                cardSummary -> cardSummary.setRemainingValue(cardSummary.getRemainingValue() + event.getAmount())
        );
    }

    @QueryHandler
    public List<CardSummary> handle(FindCardSummariesQuery query) {
        return cardSummaryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("cardId").ascending())
        ).getContent();
    }

    @QueryHandler
    public Long handle(CountCardSummariesQuery query) {
        return cardSummaryRepository.count();
    }
}
