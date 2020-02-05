package com.thecodingaviator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        int[] mayStays = {2, 2, 2, 2, 2, 4, 8};
        int[] pricesBeforeEvening = {10, 10, 10, 10, 10, 3, 2};
        int priceAfterEvening = 2;
        int day = -1;
        Scanner in = new Scanner(System.in);
        int currentDay = -1;
        double dailyTotal = 0;

        //noinspection InfiniteLoopStatement
        while (true) {
            boolean isValid;
            do {
                System.out.print("Enter the day: ");
                String choice = in.nextLine().toLowerCase();
                isValid = Arrays.asList(days).contains(choice);
                if (!isValid) System.out.println("Invalid day! Enter again!");
                else day = Arrays.asList(days).indexOf(choice);
            } while (!isValid);

            int arrivalTime = 0;
            do {
                System.out.print("Enter the arrival hour: ");
                int choice = in.nextInt();
                isValid = choice >= 8 && choice < 24;
                if (!isValid) System.out.println("Arrival time is invalid!");
                else arrivalTime = choice;
            } while (!isValid);


            int duration = 0;
            do {
                System.out.print("Enter the duration of stay: ");
                int maxPossibleStay = mayStays[day];
                if (arrivalTime >= 16) maxPossibleStay = 24 - arrivalTime;
                int choice = in.nextInt();
                isValid = choice <= maxPossibleStay && choice > 0;
                if (!isValid) System.out.println("Duration of stay is invalid!");
                else duration = choice;
            } while (!isValid);

            boolean isFrequentParker = false;
            do {
                System.out.print("Are you a frequent parker? (0/1): ");
                int choice = in.nextInt();
                isValid = (choice >= 0) && (choice <= 1);
                if (!isValid) System.out.println("Invalid input!");
                else isFrequentParker = choice == 1;
            } while (!isValid);

            if (isFrequentParker) {
                System.out.print("Enter the frequent parking number: ");
                int number = in.nextInt();
                if (number > 99999 || number < 10000) isFrequentParker = false;
                else {
                    int temp = 0;
                    int i = 0;
                    do {
                        temp += (5 - i) * (number % 10);
                        number -= number % 10;
                        number /= 10;
                        i++;
                    } while (number > 0);
                    if (temp % 11 != 0) isFrequentParker = false;
                }
                if (!isFrequentParker) System.out.println("Frequent parking number is invalid!");
            }

            double discount = arrivalTime >= 16 ? 0.5 : 0.9;
            double price;
            if (arrivalTime > 16) {
                price = duration * priceAfterEvening;
            } else {
                int exitTime = arrivalTime + duration;
                if (exitTime > 16)
                    price = ((16 - arrivalTime) * pricesBeforeEvening[day]) + (exitTime - 16) * priceAfterEvening;
                else
                    price = duration * pricesBeforeEvening[day];
            }

            price *= discount;

            System.out.println("The price is " + price);
            System.out.println("The discount applied was " + (100 - (discount * 100)));

            double payed = 0;

            do {
                System.out.print("Enter amount payed: ");
                int choice = in.nextInt();
                isValid = choice > price;
                if (!isValid) System.out.println("Invalid input!");
                else payed = choice;
            } while (!isValid);

            if(day == currentDay){
                dailyTotal += payed;
            }else{
                System.out.println("Total for previous day was " + dailyTotal);
                dailyTotal = payed;
                currentDay = day;
            }
        }
    }
}
