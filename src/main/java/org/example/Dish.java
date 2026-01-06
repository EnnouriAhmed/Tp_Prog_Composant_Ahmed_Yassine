package org.example;

import java.math.BigDecimal;
import java.util.Objects;

public class Dish {
    private final String name;
    private final BigDecimal price;
    private final DishSize size;

    public Dish(String name, BigDecimal price, DishSize size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DishSize getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.name)
                && Objects.equals(price, dish.price)
                && size == dish.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getSize());
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}
