package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryPlatform {
    private final Map<String, Order> orders = new HashMap<>();
    private final Restaurant restaurant;

    public DeliveryPlatform() {
        this(new Restaurant());
    }

    public DeliveryPlatform(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void placeOrder(Order order) {
        if (order == null) return;

        try {
            restaurant.prepare(order);
        } catch (OrderPreparationException e) {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println(e.getMessage());
        }

        orders.put(order.getId(), order);
    }

    public Optional<Order> findOrderById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return orders.values().stream()
                .filter(o -> o.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
    }
}
