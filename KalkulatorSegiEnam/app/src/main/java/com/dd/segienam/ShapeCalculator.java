package com.dd.segienam;

import java.math.BigDecimal;
import java.math.MathContext;

final class ShapeCalculator {
    private static final MathContext DISPLAY_PRECISION = new MathContext(12);
    private static final BigDecimal AREA_FACTOR =
            BigDecimal.valueOf(3 * Math.sqrt(3) / 2);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal side = dimensions[0];
        BigDecimal area = AREA_FACTOR.multiply(side.pow(2));
        BigDecimal perimeter = side.multiply(BigDecimal.valueOf(6));
        return new Measurements(area.round(DISPLAY_PRECISION), perimeter);
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
