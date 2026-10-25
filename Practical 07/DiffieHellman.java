import java.util.*;
public class DiffieHellman 
{

    // Function to calculate (base^exp) % mod
    static long powerMod(long base, long exp, long mod) {
        long result = 1;

        while (exp > 0) {
            result = (result * base) % mod;
            exp--;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Public parameters
        System.out.print("Enter public parameter p: ");
        long p = sc.nextLong();

        System.out.print("Enter public parameter g: ");
        long g = sc.nextLong();

        // Private keys
        System.out.print("Enter private key of User A: ");
        long privateA = sc.nextLong();

        System.out.print("Enter private key of User B: ");
        long privateB = sc.nextLong();

        // Generate public keys
        long publicA = powerMod(g, privateA, p);
        long publicB = powerMod(g, privateB, p);

        System.out.println("\n--- Diffie-Hellman Key Exchange ---");

        System.out.println("Public key of User A: " + publicA);
        System.out.println("Public key of User B: " + publicB);

        // Generate shared secret keys
        long sharedA = powerMod(publicB, privateA, p);
        long sharedB = powerMod(publicA, privateB, p);

        System.out.println("Shared secret at User A: " + sharedA);
        System.out.println("Shared secret at User B: " + sharedB);

        // Verify
        if (sharedA == sharedB) {
            System.out.println("\nShared Secret Keys Match!");
            System.out.println("Key Exchange Successful.");
        } else {
            System.out.println("\nShared Secret Keys Do Not Match!");
            System.out.println("Key Exchange Failed.");
        }

        sc.close();
    }
} 