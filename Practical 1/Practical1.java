import java.util.Scanner;

public class Practical1 
{
    // Function to find Modular Inverse
    public static int modInverse(int key) 
    {
        key = key % 26;

        for (int i = 1; i < 26; i++) 
        {
            if ((key * i) % 26 == 1)
                return i;
        }

        return -1;
    }

    // ================= ADDITIVE CIPHER =================

    public static String additiveEncrypt(String text, int key) 
    {a
        String result = "";

        text = text.toUpperCase();
        key = key % 26;

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int x = ch - 'A';
                int y = (x + key) % 26;

                result += (char) (y + 'A');
            }
        }

        return result;
    }

    public static String additiveDecrypt(String text, int key) 
    {
        String result = "";

        text = text.toUpperCase();
        key = key % 26;

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int x = ch - 'A';
                int y = (x - key + 26) % 26;

                result += (char) (y + 'A');
            }
        }

        return result;
    }

    // ================= MULTIPLICATIVE CIPHER =================

    public static String multiplicativeEncrypt(String text, int key) 
    {
        String result = "";

        text = text.toUpperCase();

        if (modInverse(key) == -1)
            return "Invalid Key! Modular inverse does not exist.";

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int x = ch - 'A';
                int y = (x * key) % 26;

                result += (char) (y + 'A');
            }
        }

        return result;
    }

    public static String multiplicativeDecrypt(String text, int key) 
    {
        String result = "";

        int inverse = modInverse(key);

        if (inverse == -1) 
        {
            return "Invalid Key! Modular inverse does not exist.";
        }

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int x = ch - 'A';
                int y = (x * inverse) % 26;

                result += (char) (y + 'A');
            }
        }

        return result;
    }

    // ================= AFFINE CIPHER =================

    public static String affineEncrypt(String text, int a, int b) 
    {
        String result = "";

        text = text.toUpperCase();

        if (modInverse(a) == -1)
            return "Invalid Key 'a'! Modular inverse does not exist.";

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int x = ch - 'A';

                int y = (a * x + b) % 26;

                result += (char) (y + 'A');
            }
        }

        return result;
    }

    public static String affineDecrypt(String text, int a, int b) 
    {
        String result = "";

        int inverse = modInverse(a);

        if (inverse == -1)
            return "Invalid Key 'a'! Modular inverse does not exist.";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) 
        {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) 
            {
                int y = ch - 'A';

                int x = (inverse * (y - b + 26)) % 26;

                result += (char) (x + 'A');
            }
        }

        return result;
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\n===== Classical Cipher Menu =====");
            System.out.println("1. Additive Cipher");
            System.out.println("2. Multiplicative Cipher");
            System.out.println("3. Affine Cipher");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) 
            {
                System.out.println("Program Ended.");
                break;
            }

            if (choice < 1 || choice > 4) 
            {
                System.out.println("Invalid Choice!");
                continue;
            }

            System.out.print("Enter Plaintext/Ciphertext: ");
            String text = sc.nextLine();

            switch (choice) 
            {
                // ================= ADDITIVE =================

                case 1:

                    System.out.print("Enter Key: ");
                    int key = sc.nextInt();

                    System.out.println("1. Encrypt");
                    System.out.println("2. Decrypt");

                    int ch = sc.nextInt();

                    if (ch == 1) 
                    {
                        System.out.println(
                            "Encrypted Text: " +
                            additiveEncrypt(text, key)
                        );
                    } 
                    else if (ch == 2) 
                    {
                        System.out.println(
                            "Decrypted Text: " +
                            additiveDecrypt(text, key)
                        );
                    } 
                    else 
                    {
                        System.out.println("Invalid Operation!");
                    }

                    break;

                // ================= MULTIPLICATIVE =================

                case 2:

                    System.out.print(
                        "Enter Key (1,3,5,7,9,11,15,17,19,21,23,25): "
                    );

                    int mkey = sc.nextInt();

                    System.out.println("1. Encrypt");
                    System.out.println("2. Decrypt");

                    ch = sc.nextInt();

                    if (ch == 1) 
                    {
                        System.out.println(
                            "Encrypted Text: " +
                            multiplicativeEncrypt(text, mkey)
                        );
                    } 
                    else if (ch == 2) 
                    {
                        System.out.println(
                            "Decrypted Text: " +
                            multiplicativeDecrypt(text, mkey)
                        );
                    } 
                    else 
                    {
                        System.out.println("Invalid Operation!");
                    }

                    break;

                // ================= AFFINE =================

                case 3:

                    System.out.print("Enter Multiplicative Key (a): ");
                    int a = sc.nextInt();

                    System.out.print("Enter Additive Key (b): ");
                    int b = sc.nextInt();

                    System.out.println("1. Encrypt");
                    System.out.println("2. Decrypt");

                    ch = sc.nextInt();

                    if (ch == 1) 
                    {
                        System.out.println(
                            "Encrypted Text: " +
                            affineEncrypt(text, a, b)
                        );
                    } 
                    else if (ch == 2) 
                    {
                        System.out.println(
                            "Decrypted Text: " +
                            affineDecrypt(text, a, b)
                        );
                    } 
                    else 
                    {
                        System.out.println("Invalid Operation!");
                    }

                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}