package org.example;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bienvenue chez FoodFast !");

        if (args.length == 0) {
            System.out.println("(aucun argument)");
            return;
        }

        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
