package uk.co.monotonic.java_interfaces_and_abstraction.m2.revenue;

public class RoyaltyShareCalculator implements RevenueCalculator
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
