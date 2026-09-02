import java.util.*;

public class HillCipher {

    static final int MOD = 26;

    //  Mod Function
    static int mod(int x) {
        return (x % MOD + MOD) % MOD;
    }

    //  GCD 
    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // Determinant
    static int determinant(int[][] matrix) {
        int n = matrix.length;

        if (n == 2) {
            return matrix[0][0] * matrix[1][1]
                    - matrix[0][1] * matrix[1][0];
        }

        // 3 x 3 determinant
        return matrix[0][0] *
                (matrix[1][1] * matrix[2][2]
                        - matrix[1][2] * matrix[2][1])

                - matrix[0][1] *
                (matrix[1][0] * matrix[2][2]
                        - matrix[1][2] * matrix[2][0])

                + matrix[0][2] *
                (matrix[1][0] * matrix[2][1]
                        - matrix[1][1] * matrix[2][0]);
    }

    // Modular Inverse
    static int modInverse(int a) {

        a = mod(a);

        for (int x = 1; x < MOD; x++) {
            if (mod(a * x) == 1) {
                return x;
            }
        }

        return -1;
    }

    // Matrix Inverse
    static int[][] inverseMatrix(int[][] matrix) {

        int n = matrix.length;

        int det = determinant(matrix);
        det = mod(det);

        int detInverse = modInverse(det);

        if (detInverse == -1) {
            return null;
        }

        int[][] inverse = new int[n][n];

        if (n == 2) {

            inverse[0][0] = matrix[1][1];
            inverse[0][1] = -matrix[0][1];
            inverse[1][0] = -matrix[1][0];
            inverse[1][1] = matrix[0][0];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    inverse[i][j] =
                            mod(inverse[i][j] * detInverse);
                }
            }
        }

        else if (n == 3) {

            // Cofactor matrix
            int[][] cofactor = new int[3][3];

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    int[][] minor = new int[2][2];

                    int r = 0;

                    for (int x = 0; x < 3; x++) {

                        if (x == i)
                            continue;

                        int c = 0;

                        for (int y = 0; y < 3; y++) {

                            if (y == j)
                                continue;

                            minor[r][c] = matrix[x][y];
                            c++;
                        }

                        r++;
                    }

                    int minorDet =
                            minor[0][0] * minor[1][1]
                            - minor[0][1] * minor[1][0];

                    cofactor[i][j] =
                            ((i + j) % 2 == 0)
                            ? minorDet
                            : -minorDet;
                }
            }

            // Adjoint = transpose of cofactor
            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    inverse[i][j] =
                            mod(cofactor[j][i] * detInverse);
                }
            }
        }

        return inverse;
    }

    // Plaintext
    static String cleanText(String text) {

        text = text.toUpperCase();

        StringBuilder sb = new StringBuilder();

        for (char ch : text.toCharArray()) {

            if (ch >= 'A' && ch <= 'Z') {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    // Encrypt / Decrypt
    static String process(String text, int[][] matrix) {

        int n = matrix.length;

        StringBuilder result = new StringBuilder();

        // Padding with X
        while (text.length() % n != 0) {
            text += "X";
        }

        for (int i = 0; i < text.length(); i += n) {

            int[] vector = new int[n];

            // Convert letters to numbers
            for (int j = 0; j < n; j++) {
                vector[j] = text.charAt(i + j) - 'A';
            }

            // Matrix multiplication
            for (int row = 0; row < n; row++) {

                int sum = 0;

                for (int col = 0; col < n; col++) {

                    sum += matrix[row][col] * vector[col];
                }

                result.append((char) ('A' + mod(sum)));
            }
        }

        return result.toString();
    }

    // Display Matrix
    static void displayMatrix(int[][] matrix) {

        for (int[] row : matrix) {

            for (int value : row) {
                System.out.print(value + "\t");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== HILL CIPHER =====");

        // Select matrix size
        System.out.print("Enter key matrix size (2 or 3): ");
        int n = sc.nextInt();

        if (n != 2 && n != 3) {
            System.out.println("Invalid matrix size!");
            return;
        }

        // Input key matrix
        int[][] key = new int[n][n];

        System.out.println("\nEnter Key Matrix:");

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                key[i][j] = sc.nextInt();
                key[i][j] = mod(key[i][j]);
            }
        }

        // Check determinant
        int det = mod(determinant(key));

        System.out.println("\nDeterminant modulo 26 = " + det);

        // Check invertibility
        if (gcd(det, 26) != 1) {

            System.out.println(
                "Key Matrix is NOT invertible modulo 26."
            );

            System.out.println(
                "Please enter a different key matrix."
            );

            return;
        }

        System.out.println(
            "Key Matrix is invertible modulo 26."
        );

        // Input plaintext
        sc.nextLine();

        System.out.print("\nEnter Plaintext: ");
        String plaintext = sc.nextLine();

        plaintext = cleanText(plaintext);

        if (plaintext.length() == 0) {

            System.out.println("Plaintext cannot be empty.");
            return;
        }

        // Encryption
        String encrypted = process(plaintext, key);

        System.out.println("\nPlaintext  : " + plaintext);
        System.out.println("Encrypted  : " + encrypted);

        // Find inverse key
        int[][] inverseKey = inverseMatrix(key);

        System.out.println("\nInverse Key Matrix modulo 26:");

        displayMatrix(inverseKey);

        // Decryption
        String decrypted = process(encrypted, inverseKey);

        System.out.println("\nDecrypted  : " + decrypted);

        sc.close();
    }
}