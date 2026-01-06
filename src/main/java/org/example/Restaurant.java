package org.example;

import java.util.Random;

public class Restaurant {
    private final Random random;

    public Restaurant() {
        this(new Random());
    }

    public Restaurant(Random random) {
        this.random = random;
    }

    public void prepare(Order order) throws OrderPreparationException {
        if (random.nextInt(5) == 0) {
            throw new OrderPreparationException("Preparation failed for order " + order.getId());
        }
        order.setStatus(OrderStatus.IN_PREPARATION);
    }
}
