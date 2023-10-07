package com.my.javaspringpractice.orderFood;

public class Menu {
    private MenuItems menuItems;

    public Menu(MenuItems menuItems) {
        this.menuItems = menuItems;
    }

    public void validMenu(MenuItem friedRice) {
        menuItems.getMenuNames()
                .stream()
                .filter(menuName -> menuName.equals(friedRice.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu"));
    }
}
