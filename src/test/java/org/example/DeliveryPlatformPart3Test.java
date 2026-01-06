package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlatformPart3Test {

    @Test
    void findOrdersByCustomer_returnsOnlyCustomerOrders() {
        Restaurant restaurant = new Restaurant(new Random() {
            @Override
            public int nextInt(int bound) { return 1; }
        });

        DeliveryPlatform platform = new DeliveryPlatform(restaurant);

        Customer c1 = new Customer("c1", "A", "Adr1");
        Customer c2 = new Customer("c2", "B", "Adr2");

        Order o1 = new Order(c1, LocalDateTime.now(), null);
        Order o2 = new Order(c2, LocalDateTime.now(), null);
        Order o3 = new Order(c1, LocalDateTime.now(), null);

        platform.placeOrder(o1);
        platform.placeOrder(o2);
        platform.placeOrder(o3);

        assertEquals(2, platform.findOrdersByCustomer(c1).size());
        assertEquals(1, platform.findOrdersByCustomer(c2).size());
    }

    @Test
    void findOrdersByStatus_filtersByStatus() {
        Restaurant restaurant = new Restaurant(new Random() {
            @Override
            public int nextInt(int bound) { return 1; }
        });

        DeliveryPlatform platform = new DeliveryPlatform(restaurant);

        Customer c1 = new Customer("c1", "A", "Adr1");

        Order o1 = new Order(c1, LocalDateTime.now(), null);
        Order o2 = new Order(c1, LocalDateTime.now(), null);

        platform.placeOrder(o1);
        platform.placeOrder(o2);

        assertEquals(2, platform.findOrdersByStatus(OrderStatus.IN_PREPARATION).size());
        assertEquals(0, platform.findOrdersByStatus(OrderStatus.CANCELLED).size());
    }

    @Test
    void placeOrder_whenPreparationFails_setsCancelled_andStillStoresOrder() {
        Restaurant restaurant = new Restaurant(new Random() {
            @Override
            public int nextInt(int bound) { return 0; }
        });

        DeliveryPlatform platform = new DeliveryPlatform(restaurant);

        Customer c1 = new Customer("c1", "A", "Adr1");
        Dish d1 = new Dish("Burger", new BigDecimal("10.00"), DishSize.MEDIUM);

        Map<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 1);

        Order order = new Order(c1, LocalDateTime.now(), dishes);

        platform.placeOrder(order);

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertTrue(platform.findOrderById(order.getId()).isPresent());
    }
}
