import java.math.BigInteger;
import java.util.*;

public class RSA {

    // Extended Euclidean Algorithm
    static long[] extendedGCD(long a, long b) {

        if (b == 0) {
            return new long[]{a, 1, 0};
        }

        long[] result = extendedGCD(b, a % b);

        long gcd = result[0];
        long x = result[2];
        long y = result[1] - (a / b) * result[2];

        return new long[]{gcd, x, y};
    }

    // Modular inverse
    static long modInverse(long e, long phi) {

        long[] result = extendedGCD(e, phi);

        if (result[0] != 1) {
            return -1;
        }

        long d = result[1] % phi;

        if (d < 0) {
            d = d + phi;
        }

        return d;
    }

    // Check prime number
    static boolean isPrime(long n) {

        if (n < 2)
            return false;

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number p: ");
        long p = sc.nextLong();

        System.out.print("Enter prime number q: ");
        long q = sc.nextLong();

        if (!isPrime(p) || !isPrime(q)) {
            System.out.println("Both p and q must be prime.");
            return;
        }

        long n = p * q;
        long phi = (p - 1) * (q - 1);

        // Find e automatically
        long e = 2;

        while (e < phi) {

            long[] result = extendedGCD(e, phi);

            if (result[0] == 1)
                break;

            e++;
        }

        // Find d using Extended Euclidean Algorithm
        long d = modInverse(e, phi);

        if (d == -1) {
            System.out.println("Cannot generate RSA keys.");
            return;
        }

        System.out.println("\nRSA Keys:");
        System.out.println("n = " + n);
        System.out.println("phi = " + phi);

        System.out.println("Public Key  = (" + e + ", " + n + ")");
        System.out.println("Private Key = (" + d + ", " + n + ")");

        sc.nextLine();

        System.out.print("\nEnter plaintext: ");
        String plaintext = sc.nextLine();

        // Check whether n can hold every byte
        if (n <= 255) {
            System.out.println("Choose larger prime numbers so that p*q > 255.");
            return;
        }

        // Encryption
        StringBuilder cipherText = new StringBuilder();

        for (byte b : plaintext.getBytes()) {

            int m = b & 0xFF;

            BigInteger message = BigInteger.valueOf(m);

            BigInteger encrypted = message.modPow(
                    BigInteger.valueOf(e),
                    BigInteger.valueOf(n)
            );

            cipherText.append(encrypted).append(" ");
        }

        System.out.println("\nCiphertext: " + cipherText);

        // Decryption
        String[] values = cipherText.toString().trim().split(" ");

        StringBuilder decryptedText = new StringBuilder();

        for (String value : values) {

            BigInteger cipher =
                    new BigInteger(value);

            BigInteger decrypted =
                    cipher.modPow(
                            BigInteger.valueOf(d),
                            BigInteger.valueOf(n)
                    );

            decryptedText.append(
                    (char) decrypted.intValue()
            );
        }

        System.out.println("Decrypted Text: " + decryptedText);
    }
}