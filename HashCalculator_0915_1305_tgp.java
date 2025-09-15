// 代码生成时间: 2025-09-15 13:05:09
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * A utility class to calculate hash values.
 */
public class HashCalculator {

    /**
     * Calculates the SHA-256 hash of the given input string.
     *
     * @param input The input string to hash.
     * @return The SHA-256 hash of the input string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public static String calculateSHA256(String input) throws NoSuchAlgorithmException {
        // Create a SHA-256 digest instance
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Update the digest with the input bytes
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        // Calculate the hash
        byte[] hashBytes = digest.digest();
        // Convert the hash bytes to a Base64 encoded string
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    /**
     * Main method to test the hash calculation.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Test the hash calculation with a sample input
            String input = "Hello, World!";
            String hash = calculateSHA256(input);
            System.out.println("Input: " + input);
            System.out.println("SHA-256 Hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: SHA-256 algorithm not found.");
        }
    }
}
