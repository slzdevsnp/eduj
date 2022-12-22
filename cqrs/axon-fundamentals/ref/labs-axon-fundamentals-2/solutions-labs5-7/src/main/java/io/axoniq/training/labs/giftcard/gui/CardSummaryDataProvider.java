package io.axoniq.training.labs.giftcard.gui;

import com.vaadin.data.provider.CallbackDataProvider;
import io.axoniq.training.labs.giftcard.coreapi.CardSummary;
import io.axoniq.training.labs.giftcard.coreapi.CountCardSummariesQuery;
import io.axoniq.training.labs.giftcard.coreapi.FindCardSummariesQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

@Component
public class CardSummaryDataProvider extends CallbackDataProvider<CardSummary, Void> {

    public CardSummaryDataProvider(QueryGateway queryGateway) {
        super(
                q -> queryGateway.query(
                        new FindCardSummariesQuery(q.getOffset(), q.getLimit()),
                        ResponseTypes.multipleInstancesOf(CardSummary.class)
                ).join().stream(),
                q -> queryGateway.query(new CountCardSummariesQuery(), Long.class).join().intValue()
        );
    }
}
