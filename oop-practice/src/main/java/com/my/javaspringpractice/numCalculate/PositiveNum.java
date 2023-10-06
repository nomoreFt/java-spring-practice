package com.my.javaspringpractice.numCalculate;

public class PositiveNum {
    private final int value;

    public PositiveNum(int value) {
        if(value <= 0) {
            throw new IllegalArgumentException(ExceptionMsg.NEGATIVE_NUM.getMsg());
        }
        this.value = value;
    }

    public int toInt() {
        return value;
    }

    public boolean isZero() {
        return value == 0;
    }
}
