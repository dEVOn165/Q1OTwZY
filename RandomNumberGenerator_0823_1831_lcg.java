// 代码生成时间: 2025-08-23 18:31:43
import java.security.SecureRandom;
# 添加错误处理
import java.util.Random;
# NOTE: 重要实现细节
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
# 增强安全性
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;

public class RandomNumberGenerator {

    private static Random random;
    private static SecureRandom secureRandom;
    private SqlSessionFactory sqlSessionFactory;

    public RandomNumberGenerator() {
        try {
            // Initialize Random and SecureRandom
            random = new Random();
# TODO: 优化性能
            secureRandom = new SecureRandom();
            
            // Load MyBatis configuration
# 优化算法效率
            String resource = "mybatis-config.xml";
# TODO: 优化性能
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.addMapper(RandomNumberMapper.class);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource), "development");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a pseudo-random number between min and max (inclusive).
     * 
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return A pseudo-random number between min and max.
     */
# NOTE: 重要实现细节
    public int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
        }
        
        return random.nextInt((max - min) + 1) + min;
# 增强安全性
    }

    /**
     * Generates a cryptographically strong pseudo-random number between min and max (inclusive).
     * 
     * @param min The minimum value of the range.
# FIXME: 处理边界情况
     * @param max The maximum value of the range.
     * @return A cryptographically strong pseudo-random number between min and max.
     */
    public int generateSecureRandomNumber(int min, int max) {
# NOTE: 重要实现细节
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
        }
        
        long range = (long)max - (long)min + 1;
        if (range < 0) {
            throw new IllegalArgumentException("Range must be non-negative.");
        }
        long n = (secureRandom.nextLong() & Long.MAX_VALUE) % range;
        return (int)n + min;
    }

    /**
     * Closes the SQL session factory.
     */
    public void closeSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            sqlSessionFactory.close();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            RandomNumberGenerator rng = new RandomNumberGenerator();
            System.out.println("Random Number: " + rng.generateRandomNumber(1, 100));
            System.out.println("Secure Random Number: " + rng.generateSecureRandomNumber(1, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}