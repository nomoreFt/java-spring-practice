package com.my.javaspringpractice.orderFood;

import java.util.Collection;
import java.util.List;

public class MenuItems {
    private List<MenuItem> menuItemList;

    public MenuItems(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public List<String> getMenuNames() {
        return menuItemList.stream()
                .map(MenuItem::getName)
                .toList();
    }

    public int getTotalPrice() {
        return menuItemList.stream()
                .mapToInt(MenuItem::getPrice)
                .sum();
    }


}
