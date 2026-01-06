package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final String id;
    private OrderStatus status;
    private final Map<Dish, Integer> dishes;
    private final Customer customer;
    private final LocalDateTime orderDate;

    public Order(Customer customer, LocalDateTime orderDate, Map<Dish, Integer> dishes) {
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.PENDING;
        this.customer = Objects.requireNonNull(customer, "customer must not be null");
        this.orderDate = Objects.requireNonNull(orderDate, "orderDate must not be null");
        this.dishes = new HashMap<>();
        if (dishes != null) {
            this.dishes.putAll(dishes);
        }
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = Objects.requireNonNull(status, "status must not be null");
    }

    public Map<Dish, Integer> getDishes() {
        return Map.copyOf(dishes);
    }

    public void addDish(Dish dish, int quantity) {
        if (dish == null) {
            throw new IllegalArgumentException("dish cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        dishes.merge(dish, quantity, Integer::sum);
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish dish = entry.getKey();
            Integer qty = entry.getValue();
            if (dish != null && dish.getPrice() != null && qty != null) {
                total = total.add(dish.getPrice().multiply(BigDecimal.valueOf(qty)));
            }
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id)
                && status == order.status
                && Objects.equals(dishes, order.dishes)
                && Objects.equals(customer, order.customer)
                && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getDishes(), getCustomer(), getOrderDate());
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', status=" + status + ", dishes=" + dishes + ", customer=" + customer + ", orderDate=" + orderDate + "}";
    }
}
