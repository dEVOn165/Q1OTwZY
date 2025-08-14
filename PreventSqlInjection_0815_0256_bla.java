// 代码生成时间: 2025-08-15 02:56:24
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PreventSqlInjection {

    private SqlSessionFactory sqlSessionFactory;

    // 构造函数，初始化MyBatis环境
    public PreventSqlInjection() {
        // 加载MyBatis配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 构建SqlSessionFactory
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
    }

    // 获取SqlSession对象
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    // 关闭SqlSession对象
    public void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    // 演示防止SQL注入的方法
    public void preventInjectionExample() {
        try (SqlSession sqlSession = getSqlSession()) {
            // 获取用户输入参数
            String username = "admin'; DROP TABLE users; --";

            // 使用预编译语句防止SQL注入
            String statement = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement statement = sqlSession.getConnection().prepareStatement(statement)) {
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();

                // 处理结果集...

                rs.close();
            } catch (Exception e) {
                // 错误处理
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PreventSqlInjection preventSqlInjection = new PreventSqlInjection();
        preventSqlInjection.preventInjectionExample();
    }
}
