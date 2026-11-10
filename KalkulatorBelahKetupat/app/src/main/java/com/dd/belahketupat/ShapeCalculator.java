package com.dd.belahketupat;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal firstDiagonal = dimensions[0];
        BigDecimal secondDiagonal = dimensions[1];
        BigDecimal sideSquared = firstDiagonal.divide(TWO).pow(2)
                .add(secondDiagonal.divide(TWO).pow(2));
        BigDecimal area = firstDiagonal.multiply(secondDiagonal).divide(TWO);
        BigDecimal perimeter = sqrt(sideSquared).multiply(BigDecimal.valueOf(4));
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
