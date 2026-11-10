package com.dd.segitiga;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal a = dimensions[0];
        BigDecimal b = dimensions[1];
        BigDecimal c = dimensions[2];
        BigDecimal perimeter = a.add(b).add(c);
        BigDecimal s = perimeter.divide(TWO);
        if (s.compareTo(a) <= 0 || s.compareTo(b) <= 0 || s.compareTo(c) <= 0) {
            return null;
        }

        BigDecimal areaSquared = s.multiply(s.subtract(a))
                .multiply(s.subtract(b)).multiply(s.subtract(c));
        return new Measurements(sqrt(areaSquared).round(DISPLAY_PRECISION), perimeter);
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
