// 代码生成时间: 2025-10-08 00:00:32
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图论算法实现
 * 使用MYBATIS框架与数据库交互
 * @author 你的姓名
 * @version 1.0
 */
public class GraphAlgorithm {

    // 使用MyBatis的SqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 构造函数，初始化SqlSessionFactory
     * @param reader Resource文件的Reader对象
     */
    public GraphAlgorithm(Reader reader) {
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 获取SqlSession对象
     * @return SqlSession
     */
    private SqlSession getSqlSession() {
        try {
            return sqlSessionFactory.openSession();
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            return null;
        }
    }

    /**
     * BFS算法实现
     * @param graphId 图的ID
     * @return BFS遍历的结果
     */
    public List<String> bfs(int graphId) {
        SqlSession sqlSession = getSqlSession();
        if (sqlSession == null) {
            return null;
        }
        try {
            // 调用MyBatis映射器
            GraphMapper graphMapper = sqlSession.getMapper(GraphMapper.class);
            return graphMapper.bfs(graphId);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DFS算法实现
     * @param graphId 图的ID
     * @return DFS遍历的结果
     */
    public List<String> dfs(int graphId) {
        SqlSession sqlSession = getSqlSession();
        if (sqlSession == null) {
            return null;
        }
        try {
            // 调用MyBatis映射器
            GraphMapper graphMapper = sqlSession.getMapper(GraphMapper.class);
            return graphMapper.dfs(graphId);
        } finally {
            sqlSession.close();
        }
    }

    // 定义MyBatis映射器接口
    public interface GraphMapper {

        List<String> bfs(int graphId);

        List<String> dfs(int graphId);
    }
}
