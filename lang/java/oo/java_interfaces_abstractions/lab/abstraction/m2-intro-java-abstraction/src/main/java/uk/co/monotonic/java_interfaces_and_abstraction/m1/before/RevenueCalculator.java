package uk.co.monotonic.java_interfaces_and_abstraction.m1.before;

import uk.co.monotonic.java_interfaces_and_abstraction.m1.ClientEngagement;

public class RevenueCalculator
{
    private static final double HOURLY_RATE = 50;
    private static final double FIXED_FEE = 500;
    private static final double ROYALTY_PERCENTAGE = 0.15;

    public static double price(final String method, final ClientEngagement clientEngagement)
    {
        switch (method)
        {
            case "Hourly":
                return HOURLY_RATE * clientEngagement.getHoursWorked();

            case "FixedFee":
                return FIXED_FEE;

            case "RoyaltyPercentage":
                return ROYALTY_PERCENTAGE * clientEngagement.getAnticipatedRevenue();

            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }
    }
}
