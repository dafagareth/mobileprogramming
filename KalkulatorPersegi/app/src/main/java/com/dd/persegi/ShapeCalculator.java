package com.dd.persegi;

import java.math.BigDecimal;

final class ShapeCalculator {
    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal side = dimensions[0];
        BigDecimal area = side.pow(2);
        BigDecimal perimeter = side.multiply(BigDecimal.valueOf(4));
        return new Measurements(area, perimeter);
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
