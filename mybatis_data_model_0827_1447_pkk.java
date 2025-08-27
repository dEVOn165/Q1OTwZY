// 代码生成时间: 2025-08-27 14:47:41
 * The program includes a simple User entity model, a Mapper interface,
 * and a Service class to interact with the database.
 *
 * Author: Your Name
 * Date: Today
 */

package com.example.mybatis.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * User entity model
 */
public class User {
    private Integer id;
    private String name;
    private String email;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * Mapper interface for User operations
 */
@Mapper
public interface UserMapper {
    @Select({"SELECT * FROM users WHERE id = #{id}"})
    User getUserById(@Param("id") Integer id);

    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    // Other CRUD operations can be added here
}

package com.example.mybatis.service;

import com.example.mybatis.model.User;
import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for User operations
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Retrieves a user by their ID
     *
     * @param id The ID of the user to retrieve
     * @return The User object if found, otherwise null
     */
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    /**
     * Retrieves all users
     *
     * @return A list of all User objects
     */
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    // Add additional methods for other CRUD operations
}
