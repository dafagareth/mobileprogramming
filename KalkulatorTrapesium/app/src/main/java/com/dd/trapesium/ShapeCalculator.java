package com.dd.trapesium;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal top = dimensions[0];
        BigDecimal bottom = dimensions[1];
        BigDecimal left = dimensions[2];
        BigDecimal right = dimensions[3];
        BigDecimal baseDifference = top.subtract(bottom).abs();
        BigDecimal s = baseDifference.add(left).add(right).divide(TWO);
        if (baseDifference.signum() == 0 || s.compareTo(baseDifference) <= 0
                || s.compareTo(left) <= 0 || s.compareTo(right) <= 0) {
            return null;
        }

        // Segitiga dari selisih sisi sejajar dan kedua kaki memiliki tinggi yang sama.
        BigDecimal triangleAreaSquared = s.multiply(s.subtract(baseDifference))
                .multiply(s.subtract(left)).multiply(s.subtract(right));
        BigDecimal height = sqrt(triangleAreaSquared).multiply(TWO)
                .divide(baseDifference, MathContext.DECIMAL128);
        BigDecimal area = top.add(bottom).multiply(height).divide(TWO);
        BigDecimal perimeter = top.add(bottom).add(left).add(right);
        return new Measurements(area.round(DISPLAY_PRECISION), perimeter,
                height.round(DISPLAY_PRECISION));
    }

    private static BigDecimal sqrt(BigDecimal value) {
        return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
    }

    static final class Measurements {
        final BigDecimal area;
        final BigDecimal perimeter;
        final BigDecimal height;

        Measurements(BigDecimal area, BigDecimal perimeter, BigDecimal height) {
            this.area = area;
            this.perimeter = perimeter;
            this.height = height;
        }
    }
}
