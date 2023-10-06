package com.my.javaspringpractice.numCalculate.operator;

import com.my.javaspringpractice.numCalculate.PositiveNum;

public class SubtractionOperatorNew implements NewArithmeticOperator {


    @Override
    public boolean support(String operator) {
        return operator.equals("-");
    }

    @Override
    public int calculate(PositiveNum num1, PositiveNum num2) {
        return num1.toInt() - num2.toInt();
    }
}
