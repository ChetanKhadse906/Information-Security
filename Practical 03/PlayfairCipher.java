import java.util.*;

public class PlayfairCipher 
{

    // Generate 5 x 5 Playfair matrix
    static char[][] generateMatrix(String key) 
    {
        char[][] matrix = new char[5][5];
        boolean[] used = new boolean[26];

        // I and J are treated as the same character
        used['J' - 'A'] = true;

        StringBuilder sequence = new StringBuilder();

        // Add key characters
        for (char ch : key.toUpperCase().toCharArray()) 
            {
            if (ch < 'A' || ch > 'Z')
                continue;

            if (ch == 'J')
                ch = 'I';

            if (!used[ch - 'A']) {
                sequence.append(ch);
                used[ch - 'A'] = true;
            }
        }

        // Add remaining alphabet characters
        for (char ch = 'A'; ch <= 'Z'; ch++) 
        {
            if (!used[ch - 'A']) {
                sequence.append(ch);
                used[ch - 'A'] = true;
            }
        }

        // Fill matrix
        int index = 0;

        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                matrix[i][j] = sequence.charAt(index++);
            }
        }

        return matrix;
    }

    // Display matrix
    static void displayMatrix(char[][] matrix) 
    {
        System.out.println("\nPlayfair Key Matrix:");

        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Find position of a character
    static int[] findPosition(char[][] matrix, char ch) 
    {

        if (ch == 'J')
            ch = 'I';

        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {

                if (matrix[i][j] == ch) 
                {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    // Prepare plaintext
    static String prepareText(String plaintext) 
    {

        StringBuilder text = new StringBuilder();

        for (char ch : plaintext.toUpperCase().toCharArray()) 
        {

            if (ch >= 'A' && ch <= 'Z') 
            {

                if (ch == 'J')
                    ch = 'I';

                text.append(ch);
            }
        }

        StringBuilder prepared = new StringBuilder();

        int i = 0;

        while (i < text.length()) {

            char first = text.charAt(i);

            if (i + 1 >= text.length()) 
            {
                prepared.append(first);
                prepared.append('X');
                i++;
            }

            else 
            {
                char second = text.charAt(i + 1);

                if (first == second) 
                {
                    prepared.append(first);
                    prepared.append('X');
                    i++;
                }

                else 
                {
                    prepared.append(first);
                    prepared.append(second);
                    i += 2;
                }
            }
        }

        return prepared.toString();
    }

    // Encrypt plaintext
    static String encrypt(String plaintext, char[][] matrix) 
    {

        String prepared = prepareText(plaintext);

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < prepared.length(); i += 2) 
        {

            char a = prepared.charAt(i);
            char b = prepared.charAt(i + 1);

            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);

            int rowA = posA[0];
            int colA = posA[1];

            int rowB = posB[0];
            int colB = posB[1];

            // Same row
            if (rowA == rowB) 
            {
                encrypted.append(matrix[rowA][(colA + 1) % 5]);
                encrypted.append(matrix[rowB][(colB + 1) % 5]);
            }

            // Same column
            else if (colA == colB) 
            {
                encrypted.append(matrix[(rowA + 1) % 5][colA]);
                encrypted.append(matrix[(rowB + 1) % 5][colB]);
            }

            // Rectangle rule
            else 
            {

                encrypted.append(matrix[rowA][colB]);
                encrypted.append(matrix[rowB][colA]);
            }
        }

        return encrypted.toString();
    }

    // Decrypt ciphertext
    static String decrypt(String ciphertext, char[][] matrix) 
    {

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) 
        {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);

            int rowA = posA[0];
            int colA = posA[1];

            int rowB = posB[0];
            int colB = posB[1];

            // Same row
            if (rowA == rowB) 
            {
                decrypted.append(matrix[rowA][(colA + 4) % 5]);
                decrypted.append(matrix[rowB][(colB + 4) % 5]);
            }

            // Same column
            else if (colA == colB)
            {
                decrypted.append(matrix[(rowA + 4) % 5][colA]);
                decrypted.append(matrix[(rowB + 4) % 5][colB]);
            }

            // Rectangle rule
            else 
            {
                decrypted.append(matrix[rowA][colB]);
                decrypted.append(matrix[rowB][colA]);
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        // Input key
        System.out.print("Enter Playfair key (minimum 5 alphabetic characters): ");
        String key = sc.nextLine();

        // Validate key
        while (key.replaceAll("[^a-zA-Z]", "").length() < 5) 
        {

            System.out.print("Key must contain at least 5 alphabetic characters. Enter again: ");
            key = sc.nextLine();
        }

        // Input plaintext
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        // Generate matrix
        char[][] matrix = generateMatrix(key);

        displayMatrix(matrix);

        // Encrypt
        String encrypted = encrypt(plaintext, matrix);

        System.out.println("\nPrepared Plaintext : " + prepareText(plaintext));
        System.out.println("Encrypted Text     : " + encrypted);

        // Decrypt
        String decrypted = decrypt(encrypted, matrix);

        System.out.println("Decrypted Text     : " + decrypted);

        sc.close();
    }
}