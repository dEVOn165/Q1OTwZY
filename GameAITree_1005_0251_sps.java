// 代码生成时间: 2025-10-05 02:51:20
package com.gameai;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 游戏AI行为树实现
 * 使用MYBATIS框架与数据库交互
 */
public class GameAITree {

    private SqlSessionFactory sqlSessionFactory;

    public GameAITree(DataSource dataSource) {
        // 初始化MYBATIS SqlSessionFactory
        this.sqlSessionFactory = buildSqlSessionFactory(dataSource);
    }

    /**
     * 构建MYBATIS SqlSessionFactory
     *
     * @param dataSource 数据源
     * @return SqlSessionFactory
     */
    private SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) {
        try {
            // 加载MYBATIS配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
            // 根据数据源和MYBATIS配置文件创建SqlSessionFactory
            return new SqlSessionFactoryBuilder().build(inputStream, dataSource);
        } catch (IOException e) {
            throw new RuntimeException("Error building SqlSession", e);
        }
    }

    /**
     * 执行游戏AI行为树操作
     *
     * @param action 行为树节点名称
     * @return 操作结果
     */
    public String executeAITree(String action) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取行为树节点对应的操作
            GameAIService gameAIService = sqlSession.getMapper(GameAIService.class);
            // 执行操作
            return gameAIService.executeAction(action);
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error executing AI Tree", e);
        }
    }
}

/**
 * 游戏AI行为树服务接口
 */
interface GameAIService {

    /**
     * 执行行为树节点对应的操作
     *
     * @param action 行为树节点名称
     * @return 操作结果
     */
    String executeAction(String action);
}
