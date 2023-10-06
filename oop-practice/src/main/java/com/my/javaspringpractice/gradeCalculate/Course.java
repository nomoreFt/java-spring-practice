package com.my.javaspringpractice.gradeCalculate;

public class Course {
    private final String courseName;
    private final int credits;
    private final Grade grade;

    public Course(String courseName, int credits, Grade grade) {
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
    }

    public double getGradePoint() {
        return grade.toPoint() * credits;
    }

    public int getCredits() {
        return credits;
    }
}

