import java.util.Scanner;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RSADigitalSignature 
{

    public static void main(String[] args) 
    {
        try {
            Scanner sc = new Scanner(System.in);

            // Accept message from the user
            System.out.print("Enter the plaintext message: ");
            String message = sc.nextLine();

            // Generate RSA key pair
            KeyPairGenerator keyGen =
                    KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);

            KeyPair keyPair = keyGen.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            System.out.println("\nRSA Keys Generated Successfully.");

            // Generate digital signature using private key
            Signature signer =
                    Signature.getInstance("SHA256withRSA");

            signer.initSign(privateKey);
            signer.update(message.getBytes(StandardCharsets.UTF_8));

            byte[] digitalSignature = signer.sign();

            String encodedSignature = Base64.getEncoder()
                    .encodeToString(digitalSignature);

            System.out.println("\nDigital Signature:");
            System.out.println(encodedSignature);

            // Verify signature using public key
            Signature verifier =
                    Signature.getInstance("SHA256withRSA");

            verifier.initVerify(publicKey);
            verifier.update(message.getBytes(StandardCharsets.UTF_8));

            boolean isVerified = verifier.verify(digitalSignature);

            System.out.println("\nSignature Verification:");

            if (isVerified) 
            {
                System.out.println("Digital Signature: VALID");
                System.out.println("Authenticity: Verified");
                System.out.println("Integrity: Maintained");
                System.out.println("Non-repudiation: Supported");
            } 
            else 
            {
                System.out.println("Digital Signature: INVALID");
                System.out.println("Message authenticity/integrity failed.");
            }

            // Verify integrity after changing the message
            System.out.print("\nEnter a message to test integrity: ");
            String testMessage = sc.nextLine();

            Signature testVerifier =
                    Signature.getInstance("SHA256withRSA");

            testVerifier.initVerify(publicKey);
            testVerifier.update(
                    testMessage.getBytes(StandardCharsets.UTF_8));

            boolean testResult =
                    testVerifier.verify(digitalSignature);

            if (testResult) 
            {
                System.out.println(
                        "Test Result: Signature is valid for this message.");
            } else {
                System.out.println(
                        "Test Result: Message modified or signature invalid.");
            }

            sc.close();

        } 
        catch(Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
