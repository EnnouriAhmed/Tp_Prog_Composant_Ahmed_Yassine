package org.example;

public class FoodFastUtils {

  
    public static String deliveryPlanner(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        }
        return "Aucune règle de livraison applicable";
    }

    /* public static String deliveryPlanner(int n){
        return (n % 3 == 0 && n % 5 == 0) ? "FizzBuzz"
                : (n % 3 == 0)  ? "Fizz"
                : (n % 5 == 0)  ? "Buzz"
                : "Aucune règle de livraison applicable";
    } */

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

  
    public static int sumUpTo(int n) {
        if (n < 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

 
    public static String anonymize(String text) {
        StringBuilder sb = new StringBuilder(text);
        return sb.reverse().toString();
    }
}
