package org.example;

import org.junit.jupiter.api.Test;

import static org.example.FoodFastUtils.*;
import static org.example.FoodFastUtils.sumUpTo;
import static org.junit.jupiter.api.Assertions.*;

class FoodFastUtilsTest {

    // -----------------------
    // Q2a - deliveryPlanner
    // -----------------------
    @Test
    void testDeliveryPlannerDivisibleBy3() {
        assertEquals("Fizz", deliveryPlanner(9),
                "9 est divisible par 3 → doit renvoyer 'Fizz'");
    }

    @Test
    void testDeliveryPlannerDivisibleBy5() {
        assertEquals("Buzz", deliveryPlanner(10),
                "10 est divisible par 5 → doit renvoyer 'Buzz'");
    }

    @Test
    void testDeliveryPlannerDivisibleBy3And5() {
        assertEquals("FizzBuzz", deliveryPlanner(15),
                "15 est divisible par 3 et 5 → doit renvoyer 'FizzBuzz'");
    }

    @Test
    void testDeliveryPlannerNotDivisibleBy2or3or5() {
        assertEquals("Aucune règle de livraison applicable", deliveryPlanner(7),
                "7 n’est divisible ni par 3 ni par 5 → doit renvoyer un message");
    }

    // -----------------------
    // Q2b - isLeapYear
    // -----------------------
    @Test
    void testIsLeapYearDivisibleBy4ButNot100() {
        assertTrue(isLeapYear(2024));
    }

    @Test
    void testIsLeapYearDivisibleBy100ButNot400() {
        assertFalse(isLeapYear(1900));
    }

    @Test
    void testIsLeapYearDivisibleBy400() {
        assertTrue(isLeapYear(2000));
    }

    @Test
    void testIsLeapYearNotDivisibleBy4() {
        assertFalse(isLeapYear(2023));
    }

    @Test
    void testIsLeapYearBoundaryValues() {
        assertFalse(isLeapYear(1));
        assertTrue(isLeapYear(4));
        assertFalse(isLeapYear(100));
        assertTrue(isLeapYear(400));
    }

    // -----------------------
    // Q3a - sumUpTo
    // -----------------------
    @Test
    void testSumUpToNumberEqualToZero() {
        assertEquals(0,sumUpTo(0));
    }

    @Test
    void testSumUpToNumberEqualToOne() {
        assertEquals(1,sumUpTo(1));
    }

    @Test
    void testSumUpToNumberGreaterThanZero(){
        assertEquals(10,sumUpTo(4));
        assertEquals(15,sumUpTo(5));
    }

    @Test
    void testSumUpToNumberLessThanZero(){
        assertEquals(0, sumUpTo(-2));
    }

    // -----------------------
    // Q3b - anonymize
    // -----------------------
    @Test
    void anonymize_reversesString() {
        assertEquals("tsaFdooF", FoodFastUtils.anonymize("FoodFast"));
        assertEquals("cba", FoodFastUtils.anonymize("abc"));
        assertEquals("", FoodFastUtils.anonymize(""));
    }
}
