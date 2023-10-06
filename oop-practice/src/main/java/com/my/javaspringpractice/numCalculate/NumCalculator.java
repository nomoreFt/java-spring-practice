package com.my.javaspringpractice.numCalculate;

import com.my.javaspringpractice.numCalculate.operator.*;

import java.util.List;

public class NumCalculator {

    //enum으로 계산
    public int calculate(PositiveNum num1, PositiveNum num2, String operator) {
        return ArithmeticOperator.calculate(num1, num2, operator);
    }

    List<NewArithmeticOperator> operators
     = List.of(new AdditionOperatorNew(),
               new SubtractionOperatorNew(),
               new DivisionOperatorNew(),
               new MultiplicationOperatorNew());

    public int calculate2(PositiveNum num1, PositiveNum num2, String operator) {
        NewArithmeticOperator newArithmeticOperator = operators.stream()
                .filter(op -> op.support(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMsg.INVALID_OPERATOR.getMsg()));

        return newArithmeticOperator.calculate(num1, num2);

    }
}
