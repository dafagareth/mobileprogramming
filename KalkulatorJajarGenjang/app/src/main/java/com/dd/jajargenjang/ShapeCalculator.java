package com.dd.jajargenjang;

import java.math.BigDecimal;

final class ShapeCalculator {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    private ShapeCalculator() {}

    static Measurements calculate(BigDecimal[] dimensions) {
        BigDecimal base = dimensions[0];
        BigDecimal side = dimensions[1];
        BigDecimal height = dimensions[2];
        if (height.compareTo(side) > 0) {
            return null;
        }

        BigDecimal area = base.multiply(height);
        BigDecimal perimeter = base.add(side).multiply(TWO);
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
