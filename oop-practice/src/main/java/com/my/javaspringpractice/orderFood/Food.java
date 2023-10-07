package com.my.javaspringpractice.orderFood;

import java.util.Objects;

public class Food {
    private final String name;

    public Food(String name) {
        this.name = name;
    }

    public Food(MenuItem friedRice) {
        this.name = friedRice.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
