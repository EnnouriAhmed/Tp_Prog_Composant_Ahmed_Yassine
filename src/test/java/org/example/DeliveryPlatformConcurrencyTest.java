package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlatformConcurrencyTest {

    @Test
    void placeOrder_shouldNotLoseOrders_underConcurrency() throws Exception {
        DeliveryPlatform platform = new DeliveryPlatform(new RestaurantAlwaysOk());

        int threads = 20;
        int ordersPerThread = 200;
        int total = threads * ordersPerThread;

        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threads);

        for (int t = 0; t < threads; t++) {
            final int idx = t;
            pool.submit(() -> {
                try {
                    start.await();
                    Customer c = new Customer("C" + idx, "Name" + idx, "Addr" + idx);

                    for (int i = 0; i < ordersPerThread; i++) {
                        Order o = new Order(c, LocalDateTime.now(), Map.of());
                        platform.placeOrder(o);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    fail("Interrupted");
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        assertTrue(done.await(10, TimeUnit.SECONDS), "Timeout - threads didn't finish");
        pool.shutdownNow();

        assertEquals(total, platform.countOrders());
    }

    /**
     * Restaurant stub pour rendre le test déterministe
     * (pas de 20% d'échec aléatoire).
     */
    static class RestaurantAlwaysOk extends Restaurant {
        @Override
        public void prepare(Order order) {
            order.setStatus(OrderStatus.COMPLETED);
        }
    }

    @Test
    void placeOrder_whenPreparationFails_shouldCancelAndStoreOrder() {
        DeliveryPlatform platform = new DeliveryPlatform(new RestaurantAlwaysFail());

        Customer c = new Customer("C1", "Alice", "Paris");
        Order o = new Order(c, LocalDateTime.now(), Map.of());

        platform.placeOrder(o);

        assertEquals(OrderStatus.CANCELLED, o.getStatus());
        assertTrue(platform.findOrderById(o.getId()).isPresent());
    }

    static class RestaurantAlwaysFail extends Restaurant {
        @Override
        public void prepare(Order order) throws OrderPreparationException {
            throw new OrderPreparationException("boom");
        }
    }

}
