// 代码生成时间: 2025-09-13 22:38:54
package com.example.demo;

import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CachingStrategy is a class that demonstrates the implementation of caching
 * using MyBatis framework.
 */
@Repository
public class CachingStrategy extends SqlSessionDaoSupport {

    private static final String CACHE_NAME = "example";
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Retrieves data from the cache, if available, otherwise fetches from the database.
     * @param queryId The unique identifier for the query.
     * @param parameters The parameters required for the query.
     * @param <T> The type of the data to be returned.
     * @return The cached or newly fetched data.
     */
    public <T> T getCachedData(String queryId, Object... parameters) {
        try {
            // Constructing the cache key based on the queryId and parameters
            String cacheKey = getCacheKey(queryId, parameters);

            // Check if data is available in the cache
            if (cache.containsKey(cacheKey)) {
                return (T) cache.get(cacheKey);
            } else {
                // If not in cache, fetch from the database
                return getFromDb(queryId, parameters);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the caching process
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Constructs a unique cache key based on the queryId and parameters.
     * @param queryId The unique identifier for the query.
     * @param parameters The parameters required for the query.
     * @return A string representing the cache key.
     */
    private String getCacheKey(String queryId, Object... parameters) {
        StringBuilder keyBuilder = new StringBuilder(queryId);
        for (Object param : parameters) {
            keyBuilder.append("-").append(param.toString());
        }
        return keyBuilder.toString();
    }

    /**
     * Fetches data from the database using MyBatis.
     * @param queryId The unique identifier for the query.
     * @param parameters The parameters required for the query.
     * @param <T> The type of the data to be returned.
     * @return The newly fetched data.
     */
    private <T> T getFromDb(String queryId, Object... parameters) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Get the mapper from the session
            ExampleMapper mapper = session.getMapper(ExampleMapper.class);

            // Execute the query and return the result
            return (T) mapper.executeQuery(queryId, parameters);
        } catch (Exception e) {
            // Handle any exceptions that occur during the database fetch
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * ExampleMapper is a MyBatis mapper interface.
 */
interface ExampleMapper {

    <T> T executeQuery(String queryId, Object... parameters);
}