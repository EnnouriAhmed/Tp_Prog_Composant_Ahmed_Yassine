package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void constructor_initializesIdAndPendingStatus() {
        Customer customer = new Customer("C001", "Ahmed", "Lille");
        Order order = new Order(customer, LocalDateTime.now(), null);

        assertNotNull(order.getId());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(customer, order.getCustomer());
        assertNotNull(order.getOrderDate());
    }

    @Test
    void calculateTotalPrice_sumsPrices() {
        Customer customer = new Customer("C002", "Yassine", "Lille");
        Dish d1 = new Dish("Burger", new BigDecimal("10.00"), DishSize.MEDIUM);
        Dish d2 = new Dish("Fries", new BigDecimal("3.50"), DishSize.SMALL);

        Map<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 2);
        dishes.put(d2, 1);

        Order order = new Order(customer, LocalDateTime.now(), dishes);

        assertEquals(new BigDecimal("23.50"), order.calculateTotalPrice());
    }

    @Test
    void equalsAndHashCode_sameOrderIdAndContent() {
        Customer customer = new Customer("C001", "Ahmed", "Lille");
        Dish d1 = new Dish("Burger", new BigDecimal("10.00"), DishSize.MEDIUM);

        Map<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 1);

        Order o1 = new Order(customer, LocalDateTime.of(2026, 1, 4, 12, 0), dishes);
        Order o2 = o1;

        assertEquals(o1, o2);
        assertEquals(o1.hashCode(), o2.hashCode());
    }

    @Test
    void toString_containsKeyInfo() {
        Customer customer = new Customer("C001", "Yassine", "Lille");
        Order order = new Order(customer, LocalDateTime.of(2026, 1, 4, 12, 0), null);
        String s = order.toString();

        assertTrue(s.contains(order.getId()));
        assertTrue(s.contains("PENDING"));
        assertTrue(s.contains("Customer"));
    }
}
