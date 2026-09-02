import java.util.*;

public class ColumnarTranspositionCipher 
{

    // Encryption
    public static String encrypt(String text, String key) {

        // Remove spaces
        text = text.replaceAll("\\s+", "").toUpperCase();
        key = key.toUpperCase();

        int columns = key.length();

        // Number of rows
        int rows = (int) Math.ceil((double) text.length() / columns);

        char[][] matrix = new char[rows][columns];

        int index = 0;

        // Fill matrix row-wise
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                if (index < text.length()) {
                    matrix[i][j] = text.charAt(index++);
                } else {
                    matrix[i][j] = '\0';
                }
            }
        }

        // Get column order
        Integer[] order = getColumnOrder(key);

        StringBuilder cipher = new StringBuilder();

        // Read columns according to key order
        for (int col : order) {

            for (int row = 0; row < rows; row++) {

                if (matrix[row][col] != '\0') {
                    cipher.append(matrix[row][col]);
                }
            }
        }

        return cipher.toString();
    }


    // Decryption
    public static String decrypt(String cipher, String key) {

        key = key.toUpperCase();

        int columns = key.length();
        int rows = (int) Math.ceil((double) cipher.length() / columns);

        char[][] matrix = new char[rows][columns];

        Integer[] order = getColumnOrder(key);

        int fullColumns = cipher.length() % columns;
        int index = 0;

        // Fill columns in sorted key order
        for (int col : order) {

            int columnLength;

            if (fullColumns == 0) {
                columnLength = rows;
            } else {
                // Columns from left side get one extra character
                columnLength = (col < fullColumns) ? rows : rows - 1;
            }

            for (int row = 0; row < columnLength; row++) {
                matrix[row][col] = cipher.charAt(index++);
            }
        }

        // Read row-wise
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                if (matrix[i][j] != '\0') {
                    plaintext.append(matrix[i][j]);
                }
            }
        }

        return plaintext.toString();
    }


    // Get columns according to alphabetical order of key
    public static Integer[] getColumnOrder(String key) {

        Integer[] order = new Integer[key.length()];

        for (int i = 0; i < key.length(); i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {

            char c1 = key.charAt(a);
            char c2 = key.charAt(b);

            return Character.compare(c1, c2);
        });

        return order;
    }


    public static void main(String[] args) {

        String message = "I LOVE JAVA BUT JAVA HATES ME";
        String key = "CODE";

        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original Message  : " + message);
        System.out.println("Encrypted Message : " + encrypted);
        System.out.println("Decrypted Message : " + decrypted);
    }
}