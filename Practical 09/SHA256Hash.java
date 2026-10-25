
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA256Hash 
{
    // Method to generate SHA-256 hash
    public static String generateHash(String text) throws NoSuchAlgorithmException 
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(text.getBytes());

        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) 
        {
            hexString.append(String.format("%02x", b & 0xff));
        }

        return hexString.toString();
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        try {
            // Accept original plaintext
            System.out.print("Enter original plaintext: ");
            String original = sc.nextLine();

            // Accept modified plaintext
            System.out.print("Enter modified plaintext: ");
            String modified = sc.nextLine();

            // Generate hashes
            String originalHash = generateHash(original);
            String modifiedHash = generateHash(modified);

            // Display results
            System.out.println("\nOriginal Plaintext: " + original);
            System.out.println("Original SHA-256 Hash: " + originalHash);

            System.out.println("\nModified Plaintext: " + modified);
            System.out.println("Modified SHA-256 Hash: " + modifiedHash);

            // Compare hashes
            if (originalHash.equals(modifiedHash)) 
            {
                System.out.println("\nBoth hash values are identical.");
            } 
            else 
            {
                System.out.println("\nHash values are different due to the change in plaintext.");
            }

        } 
        catch (NoSuchAlgorithmException e) 
        {
            System.out.println("SHA-256 algorithm not available.");
        }

        sc.close();
    }
}
