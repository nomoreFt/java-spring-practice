package com.my.javaspringpractice.orderFood;

public class MenuItem {
    private final String foodName;
    private final int price;

    public MenuItem(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }

    public String getName() {
        return foodName;
    }

    public int getPrice() {
        return price;
    }
}
