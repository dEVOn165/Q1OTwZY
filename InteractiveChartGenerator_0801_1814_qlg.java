// 代码生成时间: 2025-08-01 18:14:20
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.RowBounds;
import java.io.Reader;
import java.util.List;
import java.util.Map;

// 实现交互式图表生成器的类
public class InteractiveChartGenerator {

    private SqlSessionFactory sqlSessionFactory;

    // 构造函数，初始化MyBatis工厂
    public InteractiveChartGenerator() throws Exception {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    // 获取SqlSession
    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    // 关闭SqlSession
    private void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    // 生成交互式图表数据
    public List<Map<String, Object>> generateChart(String chartType) {
        SqlSession session = openSession();
        try {
            // 根据图表类型获取数据
            List<Map<String, Object>> chartData = session.selectList("chartMapper.getChartData", chartType);
            return chartData;
        } finally {
            closeSession(session);
        }
    }

    // 错误处理
    public void handleError(Exception e) {
        e.printStackTrace();
        // 可以添加更复杂的错误处理逻辑
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        try {
            InteractiveChartGenerator generator = new InteractiveChartGenerator();
            List<Map<String, Object>> chartData = generator.generateChart("line");
            chartData.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
