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
        return String.valueOf(n);
    }


    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

  
    public static int sumUpTo(int n) {
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

     public static void main(String[] args){
        System.out.println(FoodFastUtils.deliveryPlanner(2));
        System.out.println(FoodFastUtils.deliveryPlanner(3));
        System.out.println(FoodFastUtils.deliveryPlanner(5));
        System.out.println(FoodFastUtils.deliveryPlanner(7));
        System.out.println(FoodFastUtils.isLeapYear(2020));
        System.out.println(FoodFastUtils.isLeapYear(1900));
        System.out.println(sumUpTo(5));
        System.out.println(anonymize("kat"));
    }
}
