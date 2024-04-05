package ru.omarov.aviatest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class PriceDifferenceCalculator {
    private final List<BigDecimal> prices;

    public PriceDifferenceCalculator(List<BigDecimal> prices) {
        this.prices = prices.stream().sorted().toList();
    }

    public BigDecimal calculate() {
        return calculateAvg().subtract(calculateMedian()).abs();
    }

    private BigDecimal calculateMedian() {
        if (prices.size() % 2 == 0) {
            return prices.get(prices.size() / 2).add(prices.get(prices.size() / 2 - 1))
                    .divide(BigDecimal.valueOf(2), 1, RoundingMode.FLOOR);
        }
        return prices.get(prices.size() / 2);
    }

    private BigDecimal calculateAvg() {
        return prices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(prices.size()), 1, RoundingMode.FLOOR);
    }
}
