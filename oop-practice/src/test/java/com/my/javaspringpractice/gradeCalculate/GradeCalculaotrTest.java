package com.my.javaspringpractice.gradeCalculate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.security.auth.Subject;
import java.util.List;

import static com.my.javaspringpractice.gradeCalculate.Grade.*;

public class GradeCalculaotrTest {

    @Test
    @DisplayName("학점을 계산한다.")
    void calculateGradeTest() {
        GradeCalculator calculator = new GradeCalculator();
        Courses courses = new Courses(List.of(
                new Course("객체지향설계", 3, A_PLUS),
                new Course("운영체제", 3, A_PLUS),
                new Course("컴퓨터구조", 3, A_PLUS)
        ));

        double result = calculator.calculate(courses);


        Assertions.assertThat(result).isEqualTo(4.5);
    }
}
