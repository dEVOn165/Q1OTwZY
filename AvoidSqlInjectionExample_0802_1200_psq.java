// 代码生成时间: 2025-08-02 12:00:19
package com.example.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 主应用程序类，用于启动Spring Boot应用程序
 */
@SpringBootApplication
@EnableTransactionManagement
public class AvoidSqlInjectionExample {

    public static void main(String[] args) {
        SpringApplication.run(AvoidSqlInjectionExample.class, args);
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 根据用户ID查询用户信息，防止SQL注入
     * @param userId 用户ID
     * @return 用户信息
     */
    public Map<String, Object> findUserById(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.findUserById(userId);
        } catch (PersistenceException e) {
            // 处理MyBatis异常
            throw new RuntimeException("查询用户信息失败", e);
        }
    }

    /**
     * 用于演示MyBatis的Mapper接口
     */
    public interface UserMapper {
        Map<String, Object> findUserById(int userId);
    }
}
