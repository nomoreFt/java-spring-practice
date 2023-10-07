package com.my.javaspringpractice.orderFood;

public class Customer {
    public Food order(MenuItem friedRice, Menu menu, Chef chef) {
        menu.validMenu(friedRice);
        return chef.cooking(friedRice);
    }
}
