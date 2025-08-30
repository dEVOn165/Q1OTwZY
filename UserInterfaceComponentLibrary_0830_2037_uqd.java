// 代码生成时间: 2025-08-30 20:37:03
package com.example.uicomponents;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 用户界面组件库，使用MYBATIS框架实现数据库操作
 */
public class UserInterfaceComponentLibrary {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 构造函数，初始化SqlSessionFactory
     * @param resourcePath mybatis配置文件路径
     * @throws IOException 当配置文件读取失败时抛出
     */
    public UserInterfaceComponentLibrary(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 获取所有用户界面组件
     * @param sqlSession SqlSession对象
     * @return 用户界面组件列表
     */
    public List<Component> getAllComponents(SqlSession sqlSession) {
        try {
            return sqlSession.selectList("com.example.uicomponents.mapper.ComponentMapper.getAllComponents");
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加用户界面组件
     * @param sqlSession SqlSession对象
     * @param component 用户界面组件对象
     * @return 插入成功返回组件ID，失败返回-1
     */
    public int addComponent(SqlSession sqlSession, Component component) {
        try {
            int rows = sqlSession.insert("com.example.uicomponents.mapper.ComponentMapper.insertComponent", component);
            if (rows > 0) {
                sqlSession.commit();
                return component.getId();
            } else {
                sqlSession.rollback();
                return -1;
            }
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            sqlSession.rollback();
            return -1;
        }
    }

    /**
     * 更新用户界面组件
     * @param sqlSession SqlSession对象
     * @param component 用户界面组件对象
     * @return 更新成功返回更新行数，失败返回-1
     */
    public int updateComponent(SqlSession sqlSession, Component component) {
        try {
            int rows = sqlSession.update("com.example.uicomponents.mapper.ComponentMapper.updateComponent", component);
            if (rows > 0) {
                sqlSession.commit();
                return rows;
            } else {
                sqlSession.rollback();
                return -1;
            }
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            sqlSession.rollback();
            return -1;
        }
    }

    /**
     * 删除用户界面组件
     * @param sqlSession SqlSession对象
     * @param id 组件ID
     * @return 删除成功返回删除行数，失败返回-1
     */
    public int deleteComponent(SqlSession sqlSession, int id) {
        try {
            int rows = sqlSession.delete("com.example.uicomponents.mapper.ComponentMapper.deleteComponent", id);
            if (rows > 0) {
                sqlSession.commit();
                return rows;
            } else {
                sqlSession.rollback();
                return -1;
            }
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            sqlSession.rollback();
            return -1;
        }
    }

    /**
     * 获取SqlSessionFactory对象
     * @return SqlSessionFactory对象
     */
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}

/**
 * 用户界面组件对象
 */
class Component {
    private int id;
    private String name;
    private String description;

    // 省略getter和setter方法
}
