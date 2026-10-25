import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class DES 
{

    // DES Encryption
    static String encrypt(String text, String key) throws Exception {
        SecretKeySpec secretKey =
                new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return bytesToHex(encrypted);
    }

    // DES Decryption
    static String decrypt(String cipherText, String key) throws Exception {
        SecretKeySpec secretKey =
                new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encrypted = hexToBytes(cipherText);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted);
    }

    // Convert bytes to Hexadecimal
    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }

    // Convert Hexadecimal to bytes
    static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(
                    hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    // Change one bit in plaintext
    static String changeOneBit(String text) {
        byte[] data = text.getBytes();

        // Flip first bit
        data[0] = (byte) (data[0] ^ 1);

        return new String(data);
    }

    // Count different bits
    static int differentBits(String hex1, String hex2) {
        byte[] a = hexToBytes(hex1);
        byte[] b = hexToBytes(hex2);

        int count = 0;

        for (int i = 0; i < a.length; i++) {
            count += Integer.bitCount((a[i] ^ b[i]) & 0xFF);
        }

        return count;
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter DES key (8 characters): ");
        String key = sc.nextLine();

        if (key.length() != 8) {
            System.out.println("Key must be exactly 8 characters.");
            return;
        }

        // Original encryption
        String cipherText = encrypt(plaintext, key);

        System.out.println("\nOriginal Ciphertext: " + cipherText);

        // Decryption
        String decrypted = decrypt(cipherText, key);

        System.out.println("Decrypted Text: " + decrypted);

        // Avalanche Effect
        String modifiedText = changeOneBit(plaintext);

        String modifiedCipher = encrypt(modifiedText, key);

        System.out.println("\nModified Plaintext: " + modifiedText);
        System.out.println("Modified Ciphertext: " + modifiedCipher);

        int changed = differentBits(cipherText, modifiedCipher);

        System.out.println("Number of changed bits: " + changed);
    }
} 

