// 代码生成时间: 2025-08-08 13:05:41
// HashCalculator.java
/**
 * This class provides a simple hash value calculation tool using Java and MyBatis framework.
 * It includes error handling, comments, and follows Java best practices for maintainability and scalability.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashCalculator {

    // Computes the hash value of a given string using SHA-256 algorithm
    public String computeHash(String input) {
        try {
            // Get a MessageDigest instance for SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Perform the hash calculation
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception if the SHA-256 algorithm is not available
            throw new RuntimeException("Hash algorithm not available", e);
        }
    }

    // Main method for testing the HashCalculator tool
    public static void main(String[] args) {
        HashCalculator calculator = new HashCalculator();
        String input = "Hello, MyBatis!";
        try {
            String hashValue = calculator.computeHash(input);
            System.out.println("Input: " + input);
            System.out.println("Hash Value: " + hashValue);
        } catch (Exception e) {
            // Handle any runtime exceptions that may occur during the hashing process
            System.err.println("Error computing hash: " + e.getMessage());
        }
    }
}
