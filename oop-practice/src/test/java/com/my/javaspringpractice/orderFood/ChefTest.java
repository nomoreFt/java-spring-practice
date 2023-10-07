package com.my.javaspringpractice.orderFood;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ChefTest {

    @Test
    @DisplayName("Chef can cook")
    void chefCanCook() {
        Chef chef = new Chef();

        MenuItems menuItemList = new MenuItems(List.of(new MenuItem("Fried Rice", 100)));
        MenuItems menuItemList2 = new MenuItems(List.of(new MenuItem("Fried Rice", 100), new MenuItem("Fried Rice2", 200)));

        List<Food> result = chef.cooking(menuItemList);

        assertThatCode(() -> new Chef())
                .doesNotThrowAnyException();

        assertThat(result)
                .isEqualTo(List.of(new Food("Fried Rice")));
        assertThat(result)
                .isNotEqualTo(List.of(new Food("Fried Rice2")));

        assertThat(chef.cooking(menuItemList2))
                .isEqualTo(List.of(new Food("Fried Rice"), new Food("Fried Rice2")));

    }
}
