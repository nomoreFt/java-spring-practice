package com.my.javaspringpractice.gradeCalculate;

public enum Grade {
    A_PLUS(4.5),
    A_ZERO(4.0),
    B_PLUS(3.5),
    B_ZERO(3.0),
    C_PLUS(2.5),
    C_ZERO(2.0),
    D_PLUS(1.5),
    D_ZERO(1.0),
    F(0);

    private final double gradePoint;

    Grade(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    public double toPoint(){
        return gradePoint;
    }
}
