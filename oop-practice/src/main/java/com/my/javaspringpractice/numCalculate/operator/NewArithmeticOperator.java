package com.my.javaspringpractice.numCalculate.operator;

import com.my.javaspringpractice.numCalculate.PositiveNum;

public interface NewArithmeticOperator {

    public boolean support(String operator);
    public int calculate(PositiveNum num1, PositiveNum num2);
}
