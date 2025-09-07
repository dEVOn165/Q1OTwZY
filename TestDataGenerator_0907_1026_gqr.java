// 代码生成时间: 2025-09-07 10:26:03
package com.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;
import java.util.Random;

/**
 * TestDataGenerator is a utility class for generating test data using MyBatis.
 * It demonstrates how to use MyBatis with a simple configuration setup.
 */
public class TestDataGenerator {

    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String INSERT_TEST_DATA = "InsertTestDataMapper.insertTestData";

    // Utility class should not have public constructor
    private TestDataGenerator() {
    }

    /**
     * Generates a specified number of test data entries into the database.
     * 
     * @param sqlSession the MyBatis SqlSession to execute the statement
     * @param count the number of test data entries to generate
     * @throws Exception if any error occurs during data generation
     */
    public static void generateTestData(SqlSession sqlSession, int count) throws Exception {
        try {
            // Retrieve the mapper from the session
            InsertTestDataMapper mapper = sqlSession.getMapper(InsertTestDataMapper.class);
            
            Random random = new Random();
            
            for (int i = 0; i < count; i++) {
                // Generate random test data
                int id = random.nextInt(10000);
                String name = "TestName" + id;
                int age = 18 + random.nextInt(50); // Assuming age between 18 and 68
                
                // Insert the test data into the database
                mapper.insertTestData(id, name, age);
            }
        } catch (Exception e) {
            throw new Exception("Failed to generate test data: " + e.getMessage(), e);
        } finally {
            // Ensure that the session is closed after operation
            sqlSession.close();
        }
    }

    /**
     * Main method to run the test data generation.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            // Get a SqlSessionFactory from the MyBatis configuration
            Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIG);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            
            // Open a session and generate test data
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                generateTestData(sqlSession, 10); // Generate 10 test data entries
                sqlSession.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
