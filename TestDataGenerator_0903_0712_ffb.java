// 代码生成时间: 2025-09-03 07:12:02
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    /**
     * 生成指定数量的测试数据
     * 
     * @param sqlSessionFactory MyBatis SqlSessionFactory实例
     * @param mapperInterface MyBatis Mapper接口
     * @param count 要生成的测试数据数量
     * @param <T> Mapper接口的泛型类型
     */
    public <T> void generateTestData(SqlSessionFactory sqlSessionFactory, Class<T> mapperInterface, int count) {

        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取Mapper
            T mapper = sqlSession.getMapper(mapperInterface);

            // 模拟数据生成
            for (int i = 0; i < count; i++) {
                // 构造测试数据
                TestEntity testEntity = new TestEntity();
                testEntity.setId(new Random().nextLong());
                testEntity.setName("TestName" + i);
                testEntity.setValue("TestValue" + i);

                // 插入测试数据
                mapper.insertTestEntity(testEntity);
            }

            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
        }
    }

    /**
     * 测试实体类
     */
    public static class TestEntity {
        private Long id;
        private String name;
        private String value;

        // getters and setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
