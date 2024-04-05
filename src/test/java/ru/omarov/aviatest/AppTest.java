package ru.omarov.aviatest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class AppTest {

    private static List<Arguments> priceDifferenceTestCase() {
        return List.of(
                Arguments.of(DoubleStream.of(2, 4, 1, 3, 4, 10)
                                     .mapToObj(BigDecimal::valueOf)
                                     .toList(), BigDecimal.valueOf(0.5)),
                Arguments.of(DoubleStream.of(2, 4, 1, 3, 4)
                                     .mapToObj(BigDecimal::valueOf)
                                     .toList(), BigDecimal.valueOf(0.2))
        );
    }

    @ParameterizedTest
    @MethodSource("priceDifferenceTestCase")
    public void calculatePriceDifferenceTest(List<BigDecimal> nums, BigDecimal actual) {
        PriceDifferenceCalculator calculator = new PriceDifferenceCalculator(nums);
        Assertions.assertEquals(calculator.calculate(), actual);
    }
}
