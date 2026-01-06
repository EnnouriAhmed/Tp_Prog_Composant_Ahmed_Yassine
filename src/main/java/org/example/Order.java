package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
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
        this.customer = customer;
        this.orderDate = orderDate;
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
        this.status = status;
    }

    public Map<Dish, Integer> getDishes() {
        return Collections.unmodifiableMap(dishes);
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
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && status == order.status
                && Objects.equals(dishes, order.dishes)
                && Objects.equals(customer, order.customer)
                && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, dishes, customer, orderDate);
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', status=" + status + ", dishes=" + dishes + ", customer=" + customer + ", orderDate=" + orderDate + "}";
    }
}
