// 代码生成时间: 2025-09-19 10:11:31
package com.example.myapplication.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomNumberGenerator.class);
    private final SqlSessionFactory sqlSessionFactory;

    public RandomNumberGenerator(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Generates a random number between a specified minimum and maximum value.
     *
     * @param min the minimum value of the random number (inclusive)
     * @param max the maximum value of the random number (inclusive)
     * @return the generated random number
     */
    public int generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        logger.info("Generated random number: {}", randomNumber);
        return randomNumber;
    }

    /**
     * Closes the SQL session to prevent resource leaks.
     *
     * @param session the SQL session to close
     */
    private void closeSqlSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
