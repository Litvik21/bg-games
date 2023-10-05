package org.example;

import org.example.firstTask.FirstTaskService;
import org.example.firstTask.FirstTaskServiceImpl;
import org.example.secondTask.SecondTaskService;
import org.example.secondTask.SecondTaskServiceImpl;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("<<<<<<<<<< First task >>>>>>>>>>>>");
        for (int i = 0; i < 5; i++) {
            firstTask();
        }
        System.out.println("<<<<<<<<<< Second task >>>>>>>>>>>>");
        for (int i = 0; i < 3; i++) {
            secondTask();
        }
    }

    public static void firstTask() {
        FirstTaskService firstTaskService = new FirstTaskServiceImpl();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input data: ");
        String[] input = scanner.nextLine().split(" ");
        String key = input[0];
        String message = input[1];
        if (firstTaskService.checkInputData(key, message)) {
            //Getting the ciphertext table
            String[][] resultArray = firstTaskService.gettingEncryptedTable(key, message);

            //Sort columns in ascending order using numbers in the first row
            firstTaskService.sortColumns(resultArray);

            //Getting result text
            String result = firstTaskService.getEncryptedText(resultArray);
            System.out.println(result);
            System.out.print("Print right data for check: ");
            System.out.println("Check: " + scanner.nextLine().equals(result));
        }
    }


    public static void secondTask() {
        SecondTaskService secondTaskService = new SecondTaskServiceImpl();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input data: ");

        String[] sInput = scanner.nextLine().split(" ");

        int[] s = new int[sInput.length];
        for (int i = 0; i < sInput.length; i++) {
            s[i] = Integer.parseInt(sInput[i]);
        }

        String result = secondTaskService.calculateNextThreeNumbers(s);
        System.out.println(result);
        System.out.print("Print right data for check: ");
        System.out.println("Check: " + scanner.nextLine().equals(result));
    }
}