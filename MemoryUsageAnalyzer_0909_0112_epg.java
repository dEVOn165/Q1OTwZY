// 代码生成时间: 2025-09-09 01:12:10
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.Reader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import org.json.JSONObject;

public class MemoryUsageAnalyzer {

    private SqlSessionFactory sqlSessionFactory;

    public MemoryUsageAnalyzer() {
        String resource = "mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception or rethrow as a runtime exception
            throw new RuntimeException("Failed to get resource as reader.", e);
        }
    }

    /**
     * Analyzes the current memory usage of the Java Virtual Machine.
     * @return A JSON string representing the memory usage analysis.
     */
    public String analyzeMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        JSONObject memoryUsage = new JSONObject();
        memoryUsage.put("heapMemoryUsage", heapMemoryUsage.toString());
        memoryUsage.put("nonHeapMemoryUsage", nonHeapMemoryUsage.toString());

        return memoryUsage.toString();
    }

    /**
     * Closes the SqlSessionFactory to release resources.
     */
    public void close() {
        if (this.sqlSessionFactory != null) {
            this.sqlSessionFactory.close();
        }
    }

    public static void main(String[] args) {
        MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer();
        try {
            String memoryUsageAnalysis = analyzer.analyzeMemoryUsage();
            System.out.println(memoryUsageAnalysis);
        } finally {
            analyzer.close();
        }
    }
}