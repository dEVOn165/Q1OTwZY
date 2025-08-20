// 代码生成时间: 2025-08-21 07:10:48
package com.example.notification;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息通知系统
 * 这是一个简单的消息通知系统，使用MYBATIS框架实现数据库操作。
 */
public class MessageNotificationSystem {

    private static SqlSessionFactory sqlSessionFactory;

    // 初始化数据库连接和SQL会话工厂
    static {
        try {
            // 加载MYBATIS配置文件
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            // 构建SQL会话工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * 将消息插入到数据库中
     * 
     * @param message 消息内容
     * @return 是否发送成功
     */
    public boolean sendMessage(String message) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 插入消息到数据库
            boolean result = session.insert("insertMessage", message) > 0;
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取所有消息
     * 从数据库中查询所有消息
     * 
     * @return 消息列表
     */
    public List<String> getAllMessages() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<String> messages = session.selectList("selectAllMessages");
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        MessageNotificationSystem notificationSystem = new MessageNotificationSystem();
        
        // 发送消息
        boolean sendResult = notificationSystem.sendMessage("Hello, World!");
        
        // 获取所有消息
        List<String> messages = notificationSystem.getAllMessages();
        
        // 打印消息
        for (String message : messages) {
            System.out.println(message);
        }
    }
}

// 请确保MYBATIS的配置文件（mybatis-config.xml）和映射文件（MessageMapper.xml）位于项目的资源目录下。