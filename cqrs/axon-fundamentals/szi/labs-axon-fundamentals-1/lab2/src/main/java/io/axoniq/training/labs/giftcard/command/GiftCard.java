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
    public GiftCard(IssueCardCommand cmd) {
        if (cmd.getAmount() <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        apply(new CardIssuedEvent(cmd.getCardId(), cmd.getAmount()));
    }

    @CommandHandler
    public void handle(RedeemCardCommand cmd) {
        if (cmd.getAmount() <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        if (cmd.getAmount() > remainingValue) {
            throw new IllegalStateException("amount > remaining value");
        }
        if (transactions.stream().map(GiftCardTransaction::getTransactionId).anyMatch(cmd.getTransactionId()::equals)) {
            throw new IllegalStateException("TransactionId must be unique");
        }
        apply(new CardRedeemedEvent(id, cmd.getTransactionId(), cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(CardIssuedEvent evt) {
        id = evt.getCardId();
        remainingValue = evt.getAmount();
        transactions = new ArrayList<>();
    }

    @EventSourcingHandler
    protected void on(CardReimbursedEvent event) {
        this.remainingValue += event.getAmount();
    }

    @EventSourcingHandler
    public void on(CardRedeemedEvent evt) {
        remainingValue -= evt.getAmount();
        transactions.add(new GiftCardTransaction(evt.getTransactionId(), evt.getAmount()));
    }

    public GiftCard() {
        // Required by Axon
    }
}
