package uk.co.monotonic.java_interfaces_and_abstraction.m1.abstract_classes;

import uk.co.monotonic.java_interfaces_and_abstraction.m1.ClientEngagement;

public class HourlyRateCalculator extends RevenueCalculator
{
    public static final double HOURLY_RATE = 50;

    private final double hourlyRate;

    public HourlyRateCalculator(final double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculate(final ClientEngagement clientEngagement)
    {
        return hourlyRate * clientEngagement.getHoursWorked();
    }
}
