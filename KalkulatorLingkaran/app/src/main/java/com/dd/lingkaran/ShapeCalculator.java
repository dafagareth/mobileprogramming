package com.dd.lingkaran;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);
    private static final BigDecimal PI = BigDecimal.valueOf(Math.PI);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal radius = dimensions[0];
        BigDecimal area = PI.multiply(radius.pow(2));
        BigDecimal perimeter = TWO.multiply(PI).multiply(radius);
        return new Measurements(area.round(DISPLAY_PRECISION), perimeter.round(DISPLAY_PRECISION));
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
