package org.example.firstTask;

import java.util.Arrays;

public class FirstTaskServiceImpl implements FirstTaskService {
    @Override
    public String[][] gettingEncryptedTable(String key, String text) {
        //Calculating the length of rows
        int rowLength = (int) Math.ceil(text.length() / 1.5 / key.length());
        String[][] resultArray = new String[rowLength + 1][key.length()];

        int textIndex = 0; //Text index
        int keyIndex = 0; //Key index
        int iteration = 0; //To calculate an even or odd number to determine 1 or 2 letters
        for (int i = 0; i < rowLength + 1; i++) {
            for (int j = 0; j < key.length(); j++) {
                // Filling the first row of the table with numbers
                if (keyIndex < key.length()) {
                    resultArray[i][j] = String.valueOf(key.charAt(keyIndex));
                    keyIndex++;
                } else {
                    //Checking if iteration is even and string length is less than or equal to string index
                    if ((iteration % 2 == 0) && (textIndex <= text.length() - 1)) {
                        // Checking if the text index is equal to the length of the string, then it is filled with one letter
                        if (textIndex == text.length() - 1) {
                            resultArray[i][j] = String.valueOf(text.charAt(textIndex));
                            textIndex++;
                        } else {
                            // If not, then fill it in as required - with two letters.
                            resultArray[i][j] = String.valueOf(text.charAt(textIndex)) + text.charAt(textIndex + 1);
                            textIndex = textIndex + 2;
                        }
                    } else if ((iteration % 2 != 0) && (textIndex < text.length())) {
                        // If not, then fill it in as required - with two letters.
                        resultArray[i][j] = String.valueOf(text.charAt(textIndex));
                        textIndex++;
                    } else {
                        // If none of the checks passed, then fill in with a dash
                        resultArray[i][j] = "-";
                    }
                    iteration++;
                }
            }
        }

        return resultArray;
    }

    @Override
    public String[][] sortColumns(String[][] array) {
        int numCols = array[0].length;
        // Create a copy of the first row of the array (sort order)
        String[] order = array[0].clone();

        // Sort a copy
        Arrays.sort(order, 0, numCols);

        // Create a new array for sorted data
        String[][] sortedArray = new String[array.length][numCols];

        // Loop through rows and columns to rearrange data into sorted order
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < numCols; j++) {
                // Find the index in the original row for the column in sorted order
                int originalIndex = Arrays.asList(array[0]).indexOf(order[j]);
                // Copy data to sorted array
                sortedArray[i][j] = array[i][originalIndex];
            }
        }

        // Copy data from the sorted array back to the original array
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(sortedArray[i], 0, array[i], 0, numCols);
        }

        return array;
    }

    @Override
    public String getEncryptedText(String[][] resultArray) {
        int cols = resultArray[0].length;
        int rows = resultArray.length;

        StringBuilder resultString = new StringBuilder();

        for (int col = 1; col <= cols; col++) {
            for (int row = 2; row <= rows; row++) {
                String element = resultArray[row-1][col-1];

                // Checking the length of the element and its contents
                if (element.length() == 1 && !element.equals("-")) {
                    // If the length is 1 and the element is not "-", add it to the result
                    resultString.append(element);
                } else if (element.length() == 2) {
                    // If length is 2, add both characters to the result
                    resultString.append(element.charAt(0));
                    resultString.append(element.charAt(1));
                }
            }
        }

        return resultString.toString();
    }

    @Override
    public boolean checkInputData(String key, String text) {
        return key.length() > 0 && key.length() <= 9 &&
                text.length() > 0 && text.length() <= 250 &&
                text.matches("[A-Z]+");
    }
}
