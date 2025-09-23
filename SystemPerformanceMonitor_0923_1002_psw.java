// 代码生成时间: 2025-09-23 10:02:15
package com.example.monitor;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.Reader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SystemPerformanceMonitor {
    
    // Configuration XML
    private static final String RESOURCE = "mybatis-config.xml";
    
    /**
     * Main method to run the System Performance Monitor
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SqlSession session = null;
        try {
            // Get the MyBatis SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
            
            // Open a SqlSession
            session = sqlSessionFactory.openSession();
            
            // Call the method to fetch system performance data
            Map<String, Object> performanceData = fetchSystemPerformanceData(session);
            
            // Print out the performance data
            System.out.println("System Performance Data: " + performanceData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the SqlSession if it was opened
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * Get the MyBatis SqlSessionFactory from the configuration file
     */
    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = RESOURCE;
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }
    
    /**
     * Fetch system performance data from the database
     * @param session MyBatis SqlSession
     * @return A map containing system performance data
     */
    private static Map<String, Object> fetchSystemPerformanceData(SqlSession session) {
        // Define the Mapper interface for database operations
        SystemPerformanceMapper mapper = session.getMapper(SystemPerformanceMapper.class);
        
        // Execute the query and return the result
        return mapper.getSystemPerformanceData();
    }
}

/**
 * SystemPerformanceMapper.java
 * 
 * @author Your Name
 * @version 1.0
 * @date Today's Date
 */
package com.example.monitor;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Map;

@Mapper
public interface SystemPerformanceMapper {
    
    @Select("SELECT CPU_LOAD, MEMORY_USAGE, DISK_USAGE FROM system_performance")
    Map<String, Object> getSystemPerformanceData();
}
