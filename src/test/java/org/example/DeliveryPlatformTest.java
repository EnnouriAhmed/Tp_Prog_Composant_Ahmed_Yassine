package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlatformTest {

    @Test
    void placeOrder_and_findOrderById() {
        DeliveryPlatform platform = new DeliveryPlatform();
        Customer customer = new Customer("c1", "Ahmed", "Lille");
        Order order = new Order(customer, LocalDateTime.now(), null);

        platform.placeOrder(order);

        assertTrue(platform.findOrderById(order.getId()).isPresent());
        assertEquals(order, platform.findOrderById(order.getId()).get());
    }

    @Test
    void findOrderById_unknown_returnsEmpty() {
        DeliveryPlatform platform = new DeliveryPlatform();
        assertTrue(platform.findOrderById("unknown").isEmpty());
    }
}
