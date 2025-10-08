// 代码生成时间: 2025-10-09 03:10:21
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class DataEncryptionTool {
    
    // AES encryption algorithm
    private static final String ALGORITHM = "AES";
    
    // Generate a new AES key
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }
    
    // Encrypt data using AES
    public static String encryptData(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    // Decrypt data using AES
    public static String decryptData(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        try {
            // Generate a new AES key
            SecretKey key = generateAESKey();
            
            // Data to be encrypted
            String dataToEncrypt = "Hello, this is a secret message!";
            
            // Encrypt the data
            String encryptedData = encryptData(dataToEncrypt, key);
            System.out.println("Encrypted data: " + encryptedData);
            
            // Decrypt the data
            String decryptedData = decryptData(encryptedData, key);
            System.out.println("Decrypted data: " + decryptedData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}