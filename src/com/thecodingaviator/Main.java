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
        boolean isValid;
        do {
            System.out.print("Enter the day: ");
            String choice = in.nextLine();
            isValid = Arrays.asList(days).contains(choice);
            if (!isValid) System.out.println("Invalid day! Enter again!");
            else day = Arrays.asList(days).indexOf(choice);
        } while (!isValid);
        isValid = false;
        int arrivalTime = 0;
        do{
            System.out.print("Enter the arrival hour: ");
            int choice = in.nextInt();
            isValid = choice >= 8 && choice < 24;
            if(!isValid) System.out.println("Arrival time is invalid!");
            else arrivalTime = choice;
        }while(!isValid);
    }
}
