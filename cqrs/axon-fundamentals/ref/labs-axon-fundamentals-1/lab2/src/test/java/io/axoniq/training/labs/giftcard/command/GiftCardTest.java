package io.axoniq.training.labs.giftcard.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

class GiftCardTest {

    private FixtureConfiguration<GiftCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(GiftCard.class);
    }

    @Test
    void shouldIssueGiftCard() {
        fail("To be implemented");
    }

    @Test
    void shouldRedeemGiftCard() {
        fail("To be implemented");
    }

    @Test
    void shouldNotRedeemWithNegativeAmount() {
        fail("To be implemented");
    }

    @Test
    void shouldNotRedeemWhenThereIsNotEnoughMoney() {
        fail("To be implemented");
    }
}
