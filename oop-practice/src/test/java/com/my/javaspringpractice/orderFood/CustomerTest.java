package com.my.javaspringpractice.orderFood;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

public class CustomerTest {

    @Test
    @DisplayName("손님은 메뉴판을 보고 메뉴를 주문하면, 요리사가 요리를 한다.")
    void customerOrder(){
        Customer customer = new Customer();
        Chef chef = new Chef();
        MenuItems menuItems = new MenuItems(List.of(new MenuItem("Fried Rice", 100), new MenuItem("Fried Rice2", 200)));
        MenuItem friedRice = new MenuItem("Fried Rice", 100);

        Food result = customer.order(friedRice,new Menu(menuItems), chef);

        Assertions.assertThat(result)
                .isEqualTo(new Food("Fried Rice"));
    }
}
