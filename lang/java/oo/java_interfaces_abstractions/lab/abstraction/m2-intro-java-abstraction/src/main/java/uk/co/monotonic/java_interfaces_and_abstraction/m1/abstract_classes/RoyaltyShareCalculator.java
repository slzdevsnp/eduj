package uk.co.monotonic.java_interfaces_and_abstraction.m1.abstract_classes;

import uk.co.monotonic.java_interfaces_and_abstraction.m1.ClientEngagement;

public class RoyaltyShareCalculator extends RevenueCalculator
{
    public static final double STANDARD_ROYALTY_PERCENTAGE = 0.15;

    private final double royaltyPercentage;

    public RoyaltyShareCalculator(final double royaltyPercentage)
    {
        this.royaltyPercentage = royaltyPercentage;
    }

    @Override
    public double calculate(final ClientEngagement clientEngagement)
    {
        return royaltyPercentage * clientEngagement.getAnticipatedRevenue();
    }
}
