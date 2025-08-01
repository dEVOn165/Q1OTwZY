// 代码生成时间: 2025-08-01 12:43:44
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mapper.UserMapper">
    <resultMap id="userResultMap" type="com.example.model.User">
        <id property="id" column="id" />
        <result property="username" column="username" />
        <result property="password" column="password" />
    </resultMap>

    <select id="selectUserById" resultMap="userResultMap">
        SELECT * FROM users WHERE id = #{id}
    </select>
</mapper>

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import com.example.model.User;
import com.example.mapper.UserMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.example.mapper.UserMapper;

public class IntegrationTestExample {
    public static void main(String[] args) {
        try {
            // 加载MyBatis配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 获取SqlSession对象
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // 获取UserMapper对象
                UserMapper userMapper = session.getMapper(UserMapper.class);

                // 执行数据库查询操作
                User user = userMapper.selectUserById(1);

                // 输出查询结果
                System.out.println("User ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
            }
        } catch (IOException e) {
            System.err.println("Error loading MyBatis configuration: " + e.getMessage());
        }
    }
}

// User.java
package com.example.model;

public class User {
    private int id;
    private String username;
    private String password;

    // 省略getter和setter方法
}

// UserMapper.java
package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUserById(@Param("id") int id);
}
