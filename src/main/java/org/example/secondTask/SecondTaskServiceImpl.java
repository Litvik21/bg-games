package org.example.secondTask;

public class SecondTaskServiceImpl implements SecondTaskService {
    @Override
    public String calculateNextThreeNumbers(int[] s) {
        StringBuilder sb = new StringBuilder();
        // Calculate the differences between the array elements
        int[] d1 = computeDifference(s);

        // Calculate the second difference
        int d2 = d1[1] - d1[0];

        // Find the last difference
        int diff = d1[d1.length - 1];

        // Set the initial current number as the last element of the array
        int currentNumb = s[s.length - 1];

        for (int i = 0; i < 3; i++) {
            // Calculate the next number using the formula
            int nextNumb = currentNumb + diff + d2;
            // Update the current difference
            diff = nextNumb - currentNumb;
            // Update the current number
            currentNumb = nextNumb;

            // Add the next number to the resulting string
            if (i == 2) {
                sb.append(nextNumb);
            } else {
                sb.append(nextNumb).append(" ");
            }
        }

        return sb.toString();
    }

    private int[] computeDifference(int[] arr) {
        // Create a new array 'diff' with a size 1 element less than the input array 'arr'
        int[] diff = new int[arr.length - 1];
        // Iterate over 'arr' elements (except the last one)
        for (int i = 0; i < arr.length - 1; i++) {
            // Record the difference between the current element and the next one in 'diff'
            diff[i] = arr[i + 1] - arr[i];
        }
        return diff;
    }
}
