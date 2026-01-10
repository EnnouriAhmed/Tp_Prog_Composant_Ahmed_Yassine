package org.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) throws InterruptedException {

        // =====================================================================================
        // Partie 1 : Initialisation du Projet et Algorithmes de Base
        // =====================================================================================

        // ── Question 1 ───────────────────────────────────────────────────────────────────────
        System.out.println("\nBienvenue chez FoodFast !");
        for (String arg : args) {
            System.out.println("Argument : " + arg);
        }

        // ── Question 2 ───────────────────────────────────────────────────────────────────────
        System.out.println("\n===============================================");
        System.out.println("  Q2a : Planificateur de livraison (FizzBuzz) ");
        System.out.println("===============================================");

        System.out.println("deliveryPlanner(3)  → "
                + FoodFastUtils.deliveryPlanner(3));

        System.out.println("deliveryPlanner(5) → "
                + FoodFastUtils.deliveryPlanner(5));

        System.out.println("deliveryPlanner(15) → "
                + FoodFastUtils.deliveryPlanner(15));

        System.out.println("deliveryPlanner(7) → "
                + FoodFastUtils.deliveryPlanner(7));

        System.out.println("\n===============================================");
        System.out.println("  Q2b : Calcul de promotions (Année Bissextile)");
        System.out.println("===============================================");

        // Règle 1 : divisible par 400 → bissextile
        System.out.println(
                "Année 2000 → " +
                        (FoodFastUtils.isLeapYear(2000) ? "bissextile" : "non bissextile")
        );

        // Règle 2 : divisible par 100 mais pas par 400 → non bissextile
        System.out.println(
                "Année 1900 → " +
                        (FoodFastUtils.isLeapYear(1900) ? "bissextile" : "non bissextile")
        );

        // Règle 3 : divisible par 4 mais pas par 100 → bissextile
        System.out.println(
                "Année 2024 → " +
                        (FoodFastUtils.isLeapYear(2024) ? "bissextile" : "non bissextile")
        );

        // Règle 4 : pas divisible par 4 → non bissextile
        System.out.println(
                "Année 2025 → " +
                        (FoodFastUtils.isLeapYear(2025) ? "bissextile" : "non bissextile")
        );

        // ── Question 3 ───────────────────────────────────────────────────────────────────────
        System.out.println("\n===============================================");
        System.out.println("  Q3a : Somme des entiers");
        System.out.println("===============================================");

        // Cas standard
        System.out.println(
                "Somme des entiers de 1 à 4 → " +
                        FoodFastUtils.sumUpTo(4)
        );

        // Cas simple
        System.out.println(
                "Somme des entiers de 1 à 1 → " +
                        FoodFastUtils.sumUpTo(1)
        );

        // Cas zéro (limite)
        System.out.println(
                "Somme des entiers de 1 à 0 → " +
                        FoodFastUtils.sumUpTo(0)
        );

        // Cas nombre négatif
        System.out.println(
                "Somme des entiers de 1 à -3 → " +
                        FoodFastUtils.sumUpTo(-3)
        );

        System.out.println("\n===============================================");
        System.out.println("  Q3b : Anonymisation");
        System.out.println("===============================================");


        // Chaîne standard
        System.out.println(
                "Anonymisation de \"olleH\" → " +
                        FoodFastUtils.anonymize("olleH")
        );

        // Un seul caractère
        System.out.println(
                "Anonymisation de \"A\" → " +
                        FoodFastUtils.anonymize("A")
        );

        // Chaîne vide
        System.out.println(
                "Anonymisation de \"\" → \"" +
                        FoodFastUtils.anonymize("") + "\""
        );

        // Palindrome
        System.out.println(
                "Anonymisation de \"radar\" → " +
                        FoodFastUtils.anonymize("radar")
        );

        // =====================================================================================
        // Partie 2 : Modélisation Orientée Objet
        // =====================================================================================

        System.out.println("\n===============================================");
        System.out.println("  Q4 : Anonymisation");
        System.out.println("===============================================");

        // 1) Création des plats (Dish)
        Dish pizza = new Dish("Pizza Margherita", new BigDecimal("12.50"), DishSize.MEDIUM);
        Dish burger = new Dish("Burger Classic", new BigDecimal("9.90"), DishSize.LARGE);
        Dish soda = new Dish("Soda", new BigDecimal("2.50"), DishSize.SMALL);

        // 2) Création du client (Customer)
        Customer yassine = new Customer(
                "C001",
                "Yassine",
                "10 rue de Paris, Lille"
        );

        Customer ahmed = new Customer("C002", "Ahmed", "5 avenue de Lyon, Lille");

        // 3) Création de la Commande #1 (Yassine)
        Map<Dish, Integer> dishes1 = new HashMap<>();
        dishes1.put(pizza, 2);
        dishes1.put(burger, 1);
        dishes1.put(soda, 3);

        Order order1 = new Order(
                yassine,
                LocalDateTime.now(),
                dishes1
        );

        // 4) Affichage de la commande
        System.out.println("\nCommande créée avec succès !");
        System.out.println("ID commande : " + order1.getId());
        System.out.println("Client : " + yassine.getName());
        System.out.println("Statut : " + order1.getStatus());
        System.out.println("Date : " + order1.getOrderDate());

        System.out.println("\nDétails de la commande :");
        for (Map.Entry<Dish, Integer> entry : order1.getDishes().entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + dish.getName()
                    + " (" + dish.getSize() + ") x " + quantity
                    + " → " + dish.getPrice() + "€");
        }

        System.out.println("\n===============================================");
        System.out.println("  Q5 : La Plateforme de Livraison");
        System.out.println("===============================================");

        // 1) Plateforme
        DeliveryPlatform platform1 = new DeliveryPlatform();

        // 2) Commande #2 (Ahmed)
        Map<Dish, Integer> dishes2 = new HashMap<>();
        dishes2.put(burger, 1);
        dishes2.put(soda, 1);

        Order order2 = new Order(ahmed, LocalDateTime.now().minusMinutes(10), dishes2);

        // 4) Place orders
        platform1.placeOrder(order1);
        platform1.placeOrder(order2);

        System.out.println("\n--- Résumé après placeOrder ---");
        System.out.println("Order1: " + order1.getId() + " -> " + order1.getStatus());
        System.out.println("Order2: " + order2.getId() + " -> " + order2.getStatus());

        // --- Recherche par ID ---
        System.out.println("\n--- findOrderById(order1) ---");
        Optional<Order> found = platform1.findOrderById(order1.getId());
        found.ifPresentOrElse(
                o -> System.out.println("Trouvée: " + o.getId() + " / client=" + o.getCustomer().getName() + " / status=" + o.getStatus()),
                () -> System.out.println("Aucune commande trouvée.")
        );

        System.out.println("\n===============================================");
        System.out.println("  Q6 : Recherche Avancée de Commandes");
        System.out.println("===============================================");

        // --- Recherche par client ---
        System.out.println("\n--- findOrdersByCustomer(Yassine) ---");
        List<Order> yassineOrders = platform1.findOrdersByCustomer(yassine);
        System.out.println("Nombre de commandes: " + yassineOrders.size());
        for (Order o : yassineOrders) {
            System.out.println("- " + o.getId() + " / " + o.getStatus() + " / " + o.getOrderDate());
        }

        // --- Recherche par statut ---
        System.out.println("\n--- findOrdersByStatus(CANCELLED) ---");
        List<Order> cancelled = platform1.findOrdersByStatus(OrderStatus.CANCELLED);
        System.out.println("Nombre annulées: " + cancelled.size());
        for (Order o : cancelled) {
            System.out.println("- " + o.getId() + " / client=" + o.getCustomer().getName());
        }

        System.out.println("\n===============================================");
        System.out.println("  Q7 : Gestion des Erreurs de Préparation");
        System.out.println("===============================================");

        // --- Un cas "problématique" (20% de chances de lancer l'exception) ---
        Map<Dish, Integer> dishes3 = new HashMap<>();
        dishes3.put(pizza, 999); // Ne garantit rien: l'échec dépend UNIQUEMENT du Random (1/5)

        Order order3 = new Order(yassine, LocalDateTime.now().minusHours(2), dishes3);
        platform1.placeOrder(order3);
        System.out.println("Order3: " + order3.getId() + " -> " + order3.getStatus());

        // --- Forcer un échec (CANCELLED) ---
        Random alwaysFail = new Random() {
            @Override
            public int nextInt(int bound) {
                return 0; // toujours 0 -> restaurant.prepare() échoue toujours
            }
        };

        Customer newCustomer = new Customer("C003", "Rania", "Paris");

        Restaurant failingRestaurant = new Restaurant(alwaysFail);
        DeliveryPlatform platform2 = new DeliveryPlatform(failingRestaurant);

        Order order4 = new Order(newCustomer, LocalDateTime.now(), dishes1);
        platform2.placeOrder(order4);

        System.out.println("Order4 (garanti échec): " + order4.getId() + " -> " + order4.getStatus());


        // --- Cas GARANTI: succès (IN_PREPARATION) ---
        Random alwaysSuccess = new Random() {
            @Override
            public int nextInt(int bound) {
                return 1; // jamais 0 -> prepare() ne lance jamais l'exception
            }
        };

        Restaurant goodRestaurant = new Restaurant(alwaysSuccess);
        DeliveryPlatform platform3 = new DeliveryPlatform(goodRestaurant);

        Order order5 = new Order(newCustomer, LocalDateTime.now(), dishes1);
        platform3.placeOrder(order5);

        System.out.println("Order5 (garanti succès): " + order5.getId() + " -> " + order5.getStatus());

        // =====================================================================================
        // Partie 4 : Concurrence & Sécurité
        // =====================================================================================

        System.out.println("\n===============================================");
        System.out.println("  Q8 : Montée en Charge (Concurrence)");
        System.out.println("===============================================");

        DeliveryPlatform platform4 = new DeliveryPlatform();

        int restaurants = 8;
        int ordersPerRestaurant = 100;

        ExecutorService pool = Executors.newFixedThreadPool(restaurants);

        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(restaurants);

        for (int r = 0; r < restaurants; r++) {
            final int restaurantId = r;

            pool.submit(() -> {
                try {
                    start.await();
                    Customer customer = new Customer("C-" + restaurantId,
                            "Customer " + restaurantId,
                            "Address " + restaurantId);

                    for (int i = 0; i < ordersPerRestaurant; i++) {
                        Order order = new Order(customer, LocalDateTime.now(), Map.of());
                        platform4.placeOrder(order);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        done.await();

        pool.shutdown();

        System.out.println("Total orders stored: " + platform4.countOrders());
        System.out.println("In preparation: " + platform4.findOrdersByStatus(OrderStatus.IN_PREPARATION).size());
        System.out.println("Completed: " + platform4.findOrdersByStatus(OrderStatus.COMPLETED).size());
        System.out.println("Cancelled: " + platform4.findOrdersByStatus(OrderStatus.CANCELLED).size());

        System.out.println("\n===============================================");
        System.out.println("  Q9 : Persistance en Base de Données (JDBC)");
        System.out.println("===============================================");

        try (Connection conn = Db.getConnection()) {
            platform4.saveOrder(conn, order1);
            System.out.println("Order saved in DB: " + order1.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
