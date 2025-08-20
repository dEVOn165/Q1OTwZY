// 代码生成时间: 2025-08-20 22:08:23
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.Reader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.Configuration;

/**
 * CSV文件批量处理器，使用MYBATIS框架进行操作
 */
public class CSVBatchProcessor {

    private SqlSessionFactory sqlSessionFactory;

    public CSVBatchProcessor(String configLocation) throws IOException {
        // 加载MyBatis配置文件
        Reader reader = Resources.getResourceAsReader(configLocation);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        this.sqlSessionFactory = builder.build(reader);
    }

    /**
     * 处理CSV文件
     * @param csvFilePath CSV文件路径
     * @param batchInsertSize 批量插入大小
     * @param mapperClass MYBATIS Mapper接口类
     * @param <T> Mapper接口类型
     * @throws IOException 如果文件读写发生错误
     */
    public <T> void processCSV(String csvFilePath, int batchInsertSize, Class<T> mapperClass) throws IOException {
        // 打开文件
        File csvFile = new File(csvFilePath);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // 获取批量操作的SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            try {
                T mapper = sqlSession.getMapper(mapperClass);

                List<String> batchList = new java.util.ArrayList<>(batchInsertSize);
                String line;

                // 读取CSV文件并处理数据
                while ((line = br.readLine()) != null) {
                    // 假设每行CSV的数据格式是可以直接插入数据库的
                    String[] data = line.split(",");
                    if (data.length > 0) {
                        // 将数据添加到批量列表
                        batchList.add(data[0]); // 假设只用到了第一列数据
                    }

                    // 检查批量列表是否已满
                    if (batchList.size() >= batchInsertSize) {
                        mapper.batchInsert(batchList);
                        batchList.clear();
                    }
                }

                // 处理剩余的数据
                if (!batchList.isEmpty()) {
                    mapper.batchInsert(batchList);
                }

                // 提交事务
                sqlSession.commit();
            } finally {
                // 关闭SqlSession
                sqlSession.close();
            }
        } catch (IOException e) {
            // 处理文件读写错误
            throw e;
        }
    }

    /**
     * 停止并释放资源
     */
    public void stopAndRelease() {
        if (sqlSessionFactory != null) {
            sqlSessionFactory.close();
        }
    }
}
