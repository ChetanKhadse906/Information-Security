import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Hash 
{
    // Method to generate MD5 hash
    public static String generateMD5(String text) throws NoSuchAlgorithmException 
    {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] hashBytes = md.digest(text.getBytes());

        StringBuilder hash = new StringBuilder();

        for (byte b : hashBytes) 
        {
            hash.append(String.format("%02x", b));
        }

        return hash.toString();
    }

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        try {
            // Accept plaintext from user
            System.out.print("Enter the plaintext: ");
            String originalText = sc.nextLine();

            // Generate original MD5 hash
            String originalHash = generateMD5(originalText);

            // Accept modified plaintext
            System.out.print("Enter the modified plaintext: ");
            String modifiedText = sc.nextLine();

            // Generate modified MD5 hash
            String modifiedHash = generateMD5(modifiedText);

            // Display results
            System.out.println("\n----- MD5 Hash Comparison -----");

            System.out.println("Original Plaintext : " + originalText);
            System.out.println("Original MD5 Hash  : " + originalHash);

            System.out.println("\nModified Plaintext : " + modifiedText);
            System.out.println("Modified MD5 Hash  : " + modifiedHash);

            // Compare hashes
            if (originalHash.equals(modifiedHash)) {
                System.out.println("\nHash values are SAME.");
            } 
            else 
            {
                System.out.println("\nHash values are DIFFERENT.");
                System.out.println("Even a small change in plaintext changes the MD5 hash.");
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm is not available.");
        }

        sc.close();
    }
}