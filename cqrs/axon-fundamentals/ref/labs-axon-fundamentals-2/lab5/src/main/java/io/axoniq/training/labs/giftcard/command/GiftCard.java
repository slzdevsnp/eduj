package io.axoniq.training.labs.giftcard.command;

import io.axoniq.training.labs.giftcard.coreapi.CardIssuedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardReimbursedEvent;
import io.axoniq.training.labs.giftcard.coreapi.IssueCardCommand;
import io.axoniq.training.labs.giftcard.coreapi.RedeemCardCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class GiftCard {

    @AggregateIdentifier
    private String id;

    @AggregateMember
    private List<GiftCardTransaction> transactions;

    private int remainingValue;

    @CommandHandler
    public GiftCard(IssueCardCommand command) {
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        apply(new CardIssuedEvent(command.getCardId(), command.getAmount()));
    }

    @CommandHandler
    public void handle(RedeemCardCommand command) {
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        if (command.getAmount() > remainingValue) {
            throw new IllegalStateException("amount > remaining value");
        }
        if (transactions.stream().map(GiftCardTransaction::getTransactionId).anyMatch(command.getTransactionId()::equals)) {
            throw new IllegalStateException("TransactionId must be unique");
        }
        apply(new CardRedeemedEvent(id, command.getTransactionId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(CardIssuedEvent event) {
        id = event.getCardId();
        remainingValue = event.getAmount();
        transactions = new ArrayList<>();
    }

    @EventSourcingHandler
    protected void on(CardReimbursedEvent event) {
        this.remainingValue += event.getAmount();
    }

    @EventSourcingHandler
    public void on(CardRedeemedEvent event) {
        remainingValue -= event.getAmount();
        transactions.add(new GiftCardTransaction(event.getTransactionId(), event.getAmount()));
    }

    public GiftCard() {
        // Required by Axon
    }
}
