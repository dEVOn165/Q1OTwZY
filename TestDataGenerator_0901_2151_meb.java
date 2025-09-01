// 代码生成时间: 2025-09-01 21:51:29
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Random;
import java.util.UUID;

/**
 * TestDataGenerator is a utility class to generate test data using MyBatis.
 * It provides methods to insert mock data into the database for testing purposes.
 */
public class TestDataGenerator {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor to initialize the SqlSessionFactory.
     * @param sqlSessionFactory The SqlSessionFactory to be used for database interactions.
     */
    public TestDataGenerator(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Generates a random user with unique ID and inserts it into the database.
     * @return The ID of the newly inserted user.
     */
    public String generateRandomUser() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            String userId = UUID.randomUUID().toString();
            String userName = "User_" + new Random().nextInt(1000);
            // Insert the user into the database using MyBatis mapper
            session.insert("UserMapper.insertUser", new User(userId, userName));
            session.commit();
            return userId;
        } catch (Exception e) {
            // Handle any exceptions that occur during database operations
            e.printStackTrace();
            return null;
        }
    }

    /**
     * A simple User class to hold user data.
     */
    public static class User {
        private String id;
        private String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters for User class fields
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
