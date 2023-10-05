package org.example.firstTask;

public interface FirstTaskService {

    String[][] gettingEncryptedTable(String key, String text);

    String[][] sortColumns(String[][] array);

    String getEncryptedText(String[][] resultArray);

    boolean checkInputData(String key, String text);
}
