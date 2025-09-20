// 代码生成时间: 2025-09-20 16:29:03
package com.example.monitor;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SystemPerformanceMonitor {

    private static SqlSessionFactory sqlSessionFactory;
    private static Connection connection;
    private static Configuration configuration;

    /**
     * Initializes the MyBatis configuration and connection.
     */
    public static void init() {
        try {
            // Load MyBatis configuration
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();

            // Initialize JDBC connection
            String url = "jdbc:mysql://localhost:3306/performance";
            String username = "root";
            String password = "password";
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);

            connection = DriverManager.getConnection(url, props);
            configuration = new Configuration();
            configuration.setEnvironment(new Transaction(new JdbcTransactionFactory(), connection));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the MyBatis session and JDBC connection.
     */
    public static void close() {
        if (sqlSessionFactory != null) {
            try {
                sqlSessionFactory.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves system performance data.
     * 
     * @return A string representing the system performance data.
     */
    public static String getSystemPerformanceData() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Define the mapper interface
            PerformanceMapper mapper = session.getMapper(PerformanceMapper.class);
            // Call the mapper's method to retrieve performance data
            return mapper.getSystemPerformanceData();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving system performance data.";
        }
    }
}

/**
 * PerformanceMapper.java
 * 
 * @author Your Name
 * @version 1.0
 * @since 2023-04-10
 * 
 * This interface defines the MyBatis mapper for system performance data.
 */

package com.example.monitor;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PerformanceMapper {
    @Select("SELECT cpu_usage, memory_usage, disk_usage FROM system_performance")
    String getSystemPerformanceData();
}
