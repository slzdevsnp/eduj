package io.axoniq.training.labs.giftcard.query;

import io.axoniq.training.labs.giftcard.coreapi.CardIssuedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardReimbursedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardSummary;
import io.axoniq.training.labs.giftcard.coreapi.CountCardSummariesQuery;
import io.axoniq.training.labs.giftcard.coreapi.FindCardSummariesQuery;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@ProcessingGroup("card-summary")
public class CardSummaryProjection {

    private static final Logger logger = LoggerFactory.getLogger(CardSummaryProjection.class);

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
        cardSummaryRepository.findById(event.getCardId()).ifPresent(cardSummary -> {
            cardSummary.setRemainingValue(cardSummary.getRemainingValue() - event.getAmount());
            cardSummary.setNumberOfTransactions(cardSummary.getNumberOfTransactions() + 1);
        });
    }

    @EventHandler
    public void on(CardReimbursedEvent event) {
        cardSummaryRepository.findById(event.getCardId()).ifPresent(cardSummary -> {
            cardSummary.setRemainingValue(cardSummary.getRemainingValue() + event.getAmount());
            cardSummary.setNumberOfTransactions(cardSummary.getNumberOfTransactions() - 1);
        });
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

    @ResetHandler
    public void reset() {
        cardSummaryRepository.deleteAll();
        logger.info("Cleared out CardSummary table.");
    }
}
