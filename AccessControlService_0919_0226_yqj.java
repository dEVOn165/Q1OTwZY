// 代码生成时间: 2025-09-19 02:26:03
package com.example.security;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * AccessControlService provides functionality for managing user access controls.
 */
public class AccessControlService {
    private static final Logger logger = LoggerFactory.getLogger(AccessControlService.class);
    private final SqlSessionFactory sqlSessionFactory;

    /**
     * Constructs an AccessControlService with the specified SqlSessionFactory.
     * @param sqlSessionFactory the SqlSessionFactory to use for database operations
     */
    public AccessControlService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Checks if the user has access to the specified resource.
     * @param userId the ID of the user
     * @param resourceId the ID of the resource
     * @return true if the user has access, false otherwise
     */
    public boolean hasAccess(String userId, String resourceId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccessControlMapper mapper = session.getMapper(AccessControlMapper.class);
            return mapper.hasAccess(userId, resourceId);
        } catch (Exception e) {
            logger.error("Error checking access for user: {} and resource: {}", userId, resourceId, e);
            throw new RuntimeException("Database access error", e);
        }
    }

    /**
     * Assigns access permissions to a user for a resource.
     * @param userId the ID of the user
     * @param resourceId the ID of the resource
     */
    public void assignAccess(String userId, String resourceId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccessControlMapper mapper = session.getMapper(AccessControlMapper.class);
            mapper.assignAccess(userId, resourceId);
            session.commit();
        } catch (Exception e) {
            logger.error("Error assigning access for user: {} and resource: {}", userId, resourceId, e);
            throw new RuntimeException("Database access error", e);
        }
    }

    /**
     * Revokes access permissions from a user for a resource.
     * @param userId the ID of the user
     * @param resourceId the ID of the resource
     */
    public void revokeAccess(String userId, String resourceId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccessControlMapper mapper = session.getMapper(AccessControlMapper.class);
            mapper.revokeAccess(userId, resourceId);
            session.commit();
        } catch (Exception e) {
            logger.error("Error revoking access for user: {} and resource: {}", userId, resourceId, e);
            throw new RuntimeException("Database access error", e);
        }
    }
}
