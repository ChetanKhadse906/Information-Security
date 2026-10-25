public class RailFenceCipher {

    // Encryption
    public static String encrypt(String text, int rails) {

        if (rails == 1) {
            return text;
        }

        StringBuilder[] fence = new StringBuilder[rails];

        for (int i = 0; i < rails; i++) {
            fence[i] = new StringBuilder();
        }

        int row = 0;
        int direction = 1;

        for (char ch : text.toCharArray()) {

            fence[row].append(ch);

            if (row == 0) {
                direction = 1;
            } 
            else if (row == rails - 1) {
                direction = -1;
            }

            row += direction;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder rail : fence) {
            result.append(rail);
        }

        return result.toString();
    }

    // Decryption
    public static String decrypt(String cipher, int rails) {

        if (rails == 1) {
            return cipher;
        }

        int n = cipher.length();

        boolean[][] mark = new boolean[rails][n];

        int row = 0;
        int direction = 1;

        // Mark zig-zag positions
        for (int col = 0; col < n; col++) {

            mark[row][col] = true;

            if (row == 0) {
                direction = 1;
            } 
            else if (row == rails - 1) {
                direction = -1;
            }

            row += direction;
        }

        // Fill marked positions with cipher characters
        char[][] fence = new char[rails][n];

        int index = 0;

        for (int i = 0; i < rails; i++) {

            for (int j = 0; j < n; j++) {

                if (mark[i][j]) {
                    fence[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Read zig-zag
        StringBuilder result = new StringBuilder();

        row = 0;
        direction = 1;

        for (int col = 0; col < n; col++) {

            result.append(fence[row][col]);

            if (row == 0) {
                direction = 1;
            } 
            else if (row == rails - 1) {
                direction = -1;
            }

            row += direction;
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String message = "IATEYOURCHOCOLATE";

        int rails = 3;

        String encrypted = encrypt(message, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("Original Message  : " + message);
        System.out.println("Encrypted Message : " + encrypted);
        System.out.println("Decrypted Message : " + decrypted);
    }
}