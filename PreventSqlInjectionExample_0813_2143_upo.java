// 代码生成时间: 2025-08-13 21:43:11
package com.example.security;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * This class demonstrates how to prevent SQL injection using MyBatis.
 */
public class PreventSqlInjectionExample {

    private final SqlSessionFactory sqlSessionFactory;

    public PreventSqlInjectionExample(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Retrieves user details by user ID, using MyBatis and parameterized queries to prevent SQL injection.
     * 
     * @param userId The user ID to retrieve details for.
     * @return A list of user details.
     */
    public List<User> getUserDetailsById(@Param("userId") int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.example.mapper.UserMapper.getUserDetailsById", userId);
        } catch (Exception e) {
            // Handle exceptions appropriately, possibly rethrowing or logging
            throw new RuntimeException("Error retrieving user details", e);
        }
    }
}

class User {
    private int id;
    private String name;
    // Other fields, getters, and setters

    // Constructor, getters, and setters
}

/**
 * Mapper interface to define SQL queries.
 */
interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{userId}")
    List<User> getUserDetailsById(@Param("userId") int userId);
}
