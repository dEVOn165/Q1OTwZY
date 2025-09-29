// 代码生成时间: 2025-09-29 18:36:24
package com.example.richtexteditor;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapper.Mapper;
import java.io.Reader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 富文本编辑器应用程序
 * 提供富文本编辑和存储功能
 */
public class RichTextEditorApplication {

    private SqlSessionFactory sqlSessionFactory;
    private ConcurrentHashMap<String, Object> contentCache = new ConcurrentHashMap<>();

    /**
     * 构造方法，初始化MyBatis SqlSessionFactory
     */
    public RichTextEditorApplication() {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象
     */
    private SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 保存富文本内容到数据库
     * \@param content 富文本内容
     * \@param editorId 编辑器ID
     */
    public void saveContent(String content, String editorId) {
        try (SqlSession session = getSqlSession()) {
            ContentMapper mapper = session.getMapper(ContentMapper.class);
            mapper.insertContent(content, editorId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库获取富文本内容
     * \@param editorId 编辑器ID
     * \@return 富文本内容
     */
    public String getContent(String editorId) {
        if (contentCache.containsKey(editorId)) {
            return (String) contentCache.get(editorId);
        }
        try (SqlSession session = getSqlSession()) {
            ContentMapper mapper = session.getMapper(ContentMapper.class);
            String content = mapper.selectContent(editorId);
            contentCache.put(editorId, content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 富文本内容Mapper接口
     */
    public interface ContentMapper extends Mapper {
        void insertContent(String content, String editorId);
        String selectContent(String editorId);
    }
}
