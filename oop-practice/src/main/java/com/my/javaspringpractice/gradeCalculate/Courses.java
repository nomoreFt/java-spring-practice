package com.my.javaspringpractice.gradeCalculate;

import java.util.List;

public class Courses {

    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }



    //학점 * 교과목 평점 합계
    public double getGradePointSum() {
        return courses.stream()
                .mapToDouble(Course::getGradePoint)
                .sum();
    }

    //수강신청 총 학점 수
    public int getCreditsSum() {
        return courses.stream()
                .mapToInt(Course::getCredits)
                .sum();
    }
}
