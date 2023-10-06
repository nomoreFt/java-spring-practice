package com.my.javaspringpractice.gradeCalculate;

import java.util.List;

public class GradeCalculator {
    public double calculate(Courses courses) {
        return courses.getGradePointSum() / courses.getCreditsSum();
    }
}
