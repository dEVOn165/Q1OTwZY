// 代码生成时间: 2025-08-24 17:22:37
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptionDecryptionTool {

    // 加密密码
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            // 将字节转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Encryption algorithm not found", e);
        }
    }

    // 解密密码（由于密码加密是不可逆的，此方法不实现解密功能）
    public static String decryptPassword(String encryptedPassword) {
        // 由于SHA-256加密是不可逆的，我们不能解密一个加密的密码
        // 此方法留给未来的扩展，例如使用对称加密算法
        throw new UnsupportedOperationException("Decryption is not supported for SHA-256 encrypted passwords");
    }

    public static void main(String[] args) {
        // 测试密码加密解密工具
        try {
            String originalPassword = "password123";
            String encrypted = encryptPassword(originalPassword);
            System.out.println("Encrypted Password: " + encrypted);

            // 尝试解密（将抛出异常，因为SHA-256是不可逆的）
            try {
                String decrypted = decryptPassword(encrypted);
                System.out.println("Decrypted Password: " + decrypted);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
