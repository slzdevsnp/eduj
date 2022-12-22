package io.axoniq.training.labs.giftcard.command;

import io.axoniq.training.labs.giftcard.coreapi.CardIssuedEvent;
import io.axoniq.training.labs.giftcard.coreapi.CardRedeemedEvent;
import io.axoniq.training.labs.giftcard.coreapi.IssueCardCommand;
import io.axoniq.training.labs.giftcard.coreapi.RedeemCardCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.*;

class GiftCardTest {

    private static final String CARD_ID = "id";
    private static final String TX_ID = "txId";
    private static final String ISSUED_BY = "hotelAxonIQ";

    private FixtureConfiguration<GiftCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(GiftCard.class);
    }

    @Test
    void shouldIssueGiftCard() {
        fixture.givenNoPriorActivity()
               .when(new IssueCardCommand(CARD_ID, 100, ISSUED_BY))
               .expectEvents(new CardIssuedEvent(CARD_ID, 100, ISSUED_BY));
    }

    @Test
    void shouldRedeemGiftCard() {
        fixture.given(new CardIssuedEvent(CARD_ID, 100, ISSUED_BY))
               .when(new RedeemCardCommand(CARD_ID, TX_ID, 20))
               .expectEvents(new CardRedeemedEvent(CARD_ID, TX_ID, 20));
    }

    @Test
    void shouldNotRedeemWithNegativeAmount() {
        fixture.given(new CardIssuedEvent(CARD_ID, 100, ISSUED_BY))
               .when(new RedeemCardCommand(CARD_ID, TX_ID, -10))
               .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotRedeemWhenThereIsNotEnoughMoney() {
        fixture.given(new CardIssuedEvent(CARD_ID, 100, ISSUED_BY))
               .when(new RedeemCardCommand(CARD_ID, TX_ID, 110))
               .expectException(IllegalStateException.class);
    }
}
