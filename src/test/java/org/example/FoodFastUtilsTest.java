package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodFastUtilsTest {

    // -----------------------
    // Q2a - deliveryPlanner
    // -----------------------
    @Test
    void deliveryPlanner_multipleOf3_returnsFizz() {
        assertEquals("Fizz", FoodFastUtils.deliveryPlanner(3));
        assertEquals("Fizz", FoodFastUtils.deliveryPlanner(6));
    }

    @Test
    void deliveryPlanner_multipleOf5_returnsBuzz() {
        assertEquals("Buzz", FoodFastUtils.deliveryPlanner(5));
        assertEquals("Buzz", FoodFastUtils.deliveryPlanner(10));
    }

    @Test
    void deliveryPlanner_multipleOf3And5_returnsFizzBuzz() {
        assertEquals("FizzBuzz", FoodFastUtils.deliveryPlanner(15));
        assertEquals("FizzBuzz", FoodFastUtils.deliveryPlanner(30));
    }

    @Test
    void deliveryPlanner_other_returnsNumberAsString() {
        assertEquals("1", FoodFastUtils.deliveryPlanner(1));
        assertEquals("7", FoodFastUtils.deliveryPlanner(7));
    }

    // -----------------------
    // Q2b - isLeapYear
    // -----------------------
    @Test
    void isLeapYear_divisibleBy4_andNotBy100_isTrue() {
        assertTrue(FoodFastUtils.isLeapYear(2024));
        assertTrue(FoodFastUtils.isLeapYear(1996));
    }

    @Test
    void isLeapYear_divisibleBy100_andNotBy400_isFalse() {
        assertFalse(FoodFastUtils.isLeapYear(1900));
        assertFalse(FoodFastUtils.isLeapYear(2100));
    }

    @Test
    void isLeapYear_divisibleBy400_isTrue() {
        assertTrue(FoodFastUtils.isLeapYear(2000));
        assertTrue(FoodFastUtils.isLeapYear(2400));
    }

    @Test
    void isLeapYear_notDivisibleBy4_isFalse() {
        assertFalse(FoodFastUtils.isLeapYear(2023));
        assertFalse(FoodFastUtils.isLeapYear(2019));
    }

    // -----------------------
    // Q3a - sumUpTo
    // -----------------------
    @Test
    void sumUpTo_basicCases() {
        assertEquals(1, FoodFastUtils.sumUpTo(1));
        assertEquals(3, FoodFastUtils.sumUpTo(2));  // 1+2
        assertEquals(55, FoodFastUtils.sumUpTo(10));
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
