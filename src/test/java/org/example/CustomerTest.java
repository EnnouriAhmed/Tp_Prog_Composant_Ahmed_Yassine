package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void equalsAndHashCode_sameValues() {
        Customer c1 = new Customer("c001", "Ahmed", "Lille");
        Customer c2 = new Customer("c001", "Ahmed", "Lille");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void equals_differentValues() {
        Customer c1 = new Customer("c001", "Ahmed", "Lille");
        Customer c2 = new Customer("C002", "Yassine", "10 rue de Paris, Lille");

        assertNotEquals(c1, c2);
    }

    @Test
    void toString_containsKeyInfo() {
        Customer c = new Customer("id42", "Nabil", "Paris");
        String s = c.toString();
        assertTrue(s.contains("id42"));
        assertTrue(s.contains("Nabil"));
        assertTrue(s.contains("Paris"));
    }
}
