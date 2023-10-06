package com.my.javaspringpractice.numCalculate;

public enum ExceptionMsg {
    NEGATIVE_NUM("음수는 입력할 수 없습니다."),
    DIVIDE_BY_ZERO("0으로 나눌 수 없습니다."),
    INVALID_OPERATOR("사칙연산 기호가 아닙니다.");

    private final String msg;

    ExceptionMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
