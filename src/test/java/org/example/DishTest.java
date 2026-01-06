package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void equalsAndHashCode_sameValues() {
        Dish d1 = new Dish("Burger", new BigDecimal("9.99"), DishSize.MEDIUM);
        Dish d2 = new Dish("Burger", new BigDecimal("9.99"), DishSize.MEDIUM);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void equals_differentValues() {
        Dish d1 = new Dish("Burger", new BigDecimal("9.99"), DishSize.MEDIUM);
        Dish d2 = new Dish("Burger", new BigDecimal("10.99"), DishSize.MEDIUM);

        assertNotEquals(d1, d2);
    }

    @Test
    void toString_containsKeyInfo() {
        Dish d = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        String s = d.toString();
        assertTrue(s.contains("Pizza"));
        assertTrue(s.contains("12.50"));
        assertTrue(s.contains("LARGE"));
    }
}
