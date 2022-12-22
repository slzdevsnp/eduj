package io.axoniq.training.labs.giftcard.command;


import io.axoniq.training.labs.giftcard.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

class GiftCardTest {

    private static final String CARD_ID = "id";
    private static final String TX_ID = "txId";

    private FixtureConfiguration<GiftCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(GiftCard.class);
    }

    @Test
    void shouldIssueGiftCard() {

        fixture.givenNoPriorActivity()
                .when( new IssueCardCommand(CARD_ID,99))
                .expectEvents(new CardIssuedEvent(CARD_ID, 99));
    }

    @Test
    void shouldRedeemGiftCard() {

        fixture.given(new CardIssuedEvent(CARD_ID, 100))
                .when(new RedeemCardCommand(CARD_ID, TX_ID, 20))
                .expectEvents(new CardRedeemedEvent(CARD_ID, TX_ID, 20)) ;


    }


    @Test
    void shouldNotRedeemWithNegativeAmount() {
        fixture.given(new CardIssuedEvent(CARD_ID, 100))
                .when(new RedeemCardCommand(CARD_ID, TX_ID, -10))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotRedeemWhenThereIsNotEnoughMoney() {
        fixture.given(new CardIssuedEvent(CARD_ID, 100))
                .when(new RedeemCardCommand(CARD_ID, TX_ID, 110))
                .expectException(IllegalStateException.class);
    }

}
