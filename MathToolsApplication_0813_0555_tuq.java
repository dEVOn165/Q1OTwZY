// 代码生成时间: 2025-08-13 05:55:26
package com.example.mathtools;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MathToolsApplication {

    // Configuration file path
    private static final String CONFIG_FILE = "mybatis-config.xml";

    // Factory for obtaining SqlSession instances
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // Initializing SqlSessionFactory
            String resource = MathToolsApplication.class.getResource(CONFIG_FILE).toString();
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Get a SqlSession instance
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Perform calculations and close the session
            performCalculations(session);
        }
    }

    // Method to perform mathematical calculations
    private static void performCalculations(SqlSession session) {
        // Create a map to hold parameters
        Map<String, Object> params = new HashMap<>();

        // Example calculations
        double resultAdd = calculateAdd(session, 10.0, 20.0);
        double resultSubtract = calculateSubtract(session, 10.0, 20.0);
        double resultMultiply = calculateMultiply(session, 10.0, 20.0);
        double resultDivide = calculateDivide(session, 10.0, 20.0);

        // Output results
        System.out.println("Addition Result: " + resultAdd);
        System.out.println("Subtraction Result: " + resultSubtract);
        System.out.println("Multiplication Result: " + resultMultiply);
        System.out.println("Division Result: " + resultDivide);
    }

    // Method to calculate addition
    private static double calculateAdd(SqlSession session, double a, double b) {
        params.clear();
        params.put("a", a);
        params.put("b", b);
        // Call MyBatis mapper to perform addition
        double result = session.selectOne("MathMapper.add", params);
        return result;
    }

    // Method to calculate subtraction
    private static double calculateSubtract(SqlSession session, double a, double b) {
        params.clear();
        params.put("a", a);
        params.put("b", b);
        // Call MyBatis mapper to perform subtraction
        double result = session.selectOne("MathMapper.subtract", params);
        return result;
    }

    // Method to calculate multiplication
    private static double calculateMultiply(SqlSession session, double a, double b) {
        params.clear();
        params.put("a", a);
        params.put("b", b);
        // Call MyBatis mapper to perform multiplication
        double result = session.selectOne("MathMapper.multiply", params);
        return result;
    }

    // Method to calculate division
    private static double calculateDivide(SqlSession session, double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        params.clear();
        params.put("a", a);
        params.put("b", b);
        // Call MyBatis mapper to perform division
        double result = session.selectOne("MathMapper.divide", params);
        return result;
    }
}
