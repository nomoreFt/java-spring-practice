package com.my.javaspringpractice.orderFood;

import java.util.List;

public class Chef {
    public Food cooking(MenuItem friedRice) {

        return new Food(friedRice);
    }

    public List<Food> cooking(MenuItems menuItemList) {
        return menuItemList.getMenuNames()
                .stream()
                .map(Food::new)
                .toList();
    }
}
