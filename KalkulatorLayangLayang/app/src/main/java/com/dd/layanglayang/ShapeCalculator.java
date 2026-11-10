package com.dd.layanglayang;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal upper = dimensions[0];
        BigDecimal lower = dimensions[1];
        BigDecimal across = dimensions[2];
        BigDecimal halfAcrossSquared = across.divide(TWO).pow(2);
        BigDecimal upperSide = sqrt(upper.pow(2).add(halfAcrossSquared));
        BigDecimal lowerSide = sqrt(lower.pow(2).add(halfAcrossSquared));
        BigDecimal area = upper.add(lower).multiply(across).divide(TWO);
        BigDecimal perimeter = upperSide.add(lowerSide).multiply(TWO);
        return new Measurements(area, perimeter.round(DISPLAY_PRECISION));
    }

    private static BigDecimal sqrt(BigDecimal value) {
        return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
    }

    static final class Measurements {
        final BigDecimal area;
        final BigDecimal perimeter;

        Measurements(BigDecimal area, BigDecimal perimeter) {
            this.area = area;
            this.perimeter = perimeter;
        }
    }
}
