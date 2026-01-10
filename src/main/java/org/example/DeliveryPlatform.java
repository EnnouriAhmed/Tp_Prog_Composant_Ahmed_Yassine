package org.example;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DeliveryPlatform {

    private final ConcurrentMap<String, Order> orders = new ConcurrentHashMap<>();
    private final Restaurant restaurant;

    public DeliveryPlatform() {
        this(new Restaurant());
    }

    public DeliveryPlatform(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Spec: synchroniser les méthodes qui modifient l'état
    public synchronized void placeOrder(Order order) {
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
        if (customer == null) return List.of();
        return orders.values().stream()
                .filter(o -> customer.equals(o.getCustomer()))
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        if (status == null) return List.of();
        return orders.values().stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
    }

    // utilitaire pour tests / main
    public int countOrders() {
        return orders.size();
    }

    public void saveOrder(Connection conn, Order order) throws SQLException {
        String sql = """
        INSERT INTO orders (id, status, customer_id, order_date, total_price)
        VALUES (?, ?, ?, ?, ?)
        ON CONFLICT (id) DO UPDATE SET
            status = EXCLUDED.status,
            customer_id = EXCLUDED.customer_id,
            order_date = EXCLUDED.order_date,
            total_price = EXCLUDED.total_price
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getId());
            ps.setString(2, order.getStatus().name());
            ps.setString(3, order.getCustomer().getId());
            ps.setTimestamp(4, Timestamp.valueOf(order.getOrderDate()));
            ps.setBigDecimal(5, order.calculateTotalPrice());

            ps.executeUpdate();
        }
    }
}
