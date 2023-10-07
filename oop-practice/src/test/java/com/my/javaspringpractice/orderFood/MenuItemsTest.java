package com.my.javaspringpractice.orderFood;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemsTest {

    @Test
    @DisplayName("MenuItems 에서 메뉴 이름을 가져올 수 있다.")
    void getFoodNamesFromList(){
        MenuItems menuItems = new MenuItems(List.of(new MenuItem("Fried Rice", 100), new MenuItem("Fried Rice2", 200)));

        List<String> result = menuItems.getMenuNames();

        assertEquals(List.of("Fried Rice", "Fried Rice2"), result);
    }

    @Test
    @DisplayName("MenuItems 에서 메뉴 가격을 가져올 수 있다.")
    void getFoodTotalPrice(){
        MenuItems menuItems = new MenuItems(List.of(new MenuItem("Fried Rice", 100), new MenuItem("Fried Rice2", 200)));

        int totalPrice = menuItems.getTotalPrice();

        assertEquals(300, totalPrice);
    }

}