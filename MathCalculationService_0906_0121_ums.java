// 代码生成时间: 2025-09-06 01:21:33
package com.example.mathtool;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.Reader;
import java.math.BigDecimal;

/**
 * This class provides a set of mathematical calculation functionalities.
 */
public class MathCalculationService {

    private static final String RESOURCE = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    static {
        try {
            // Load MyBatis configuration file
            Reader reader = Resources.getResourceAsReader(RESOURCE);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    public MathCalculationService() {
        // Get a new SqlSession
        this.sqlSession = sqlSessionFactory.openSession();
    }

    /**
     * Adds two numbers and returns the result.
     *
     * @param number1 The first number to add.
     * @param number2 The second number to add.
     * @return The sum of the two numbers.
     */
    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    /**
     * Subtracts one number from another and returns the result.
     *
     * @param number1 The number to subtract from.
     * @param number2 The number to subtract.
     * @return The difference between the two numbers.
     */
    public BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2);
    }

    /**
     * Multiplies two numbers and returns the result.
     *
     * @param number1 The first number to multiply.
     * @param number2 The second number to multiply.
     * @return The product of the two numbers.
     */
    public BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2);
    }

    /**
     * Divides one number by another and returns the result.
     *
     * @param number1 The number to divide.
     * @param number2 The number to divide by.
     * @return The quotient of the two numbers.
     * @throws ArithmeticException if division by zero occurs.
     */
    public BigDecimal divide(BigDecimal number1, BigDecimal number2) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return number1.divide(number2);
    }

    /**
     * Closes the SqlSession to release resources.
     */
    public void close() {
        sqlSession.close();
    }
}
