// 代码生成时间: 2025-08-14 12:12:27
package performance;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * PerformanceTest class to measure the performance of MyBatis operations
 * using multiple threads.
 */
public class PerformanceTest {

    private static final int THREAD_COUNT = 10; // Number of concurrent threads
    private static final int OPERATION_COUNT = 1000; // Number of operations per thread
    private static final int WARM_UP_ROUNDS = 5; // Warm-up rounds before measuring performance
    private static final int MEASUREMENT_ROUNDS = 10; // Number of rounds to measure performance

    // The MyBatis configuration file path
    private static final String CONFIGURATION = "mybatis-config.xml";

    public static void main(String[] args) {
        try {
            // Initialize the SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

            // Warm-up phase
            warmUp(sqlSessionFactory);

            // Measure performance
            measurePerformance(sqlSessionFactory);

        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        // Get the MyBatis configuration file reader
        Reader reader = Resources.getResourceAsReader(CONFIGURATION);

        // Build the SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // Close the reader
        reader.close();

        return sqlSessionFactory;
    }

    private static void warmUp(SqlSessionFactory sqlSessionFactory) {
        // Create a thread pool for warm-up
        ExecutorService warmUpExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Perform warm-up operations
        for (int i = 0; i < WARM_UP_ROUNDS; i++) {
            for (int j = 0; j < THREAD_COUNT; j++) {
                warmUpExecutor.execute(() -> {
                    try (SqlSession session = sqlSessionFactory.openSession()) {
                        // Perform a dummy operation (e.g., select 1)
                        session.selectOne("selectOne");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        // Shutdown the executor
        warmUpExecutor.shutdown();
        try {
            warmUpExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void measurePerformance(SqlSessionFactory sqlSessionFactory) {
        // Create a thread pool for performance measurement
        ExecutorService performanceExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Record the start time
        long startTime = System.nanoTime();

        // Perform performance measurement operations
        for (int i = 0; i < MEASUREMENT_ROUNDS; i++) {
            for (int j = 0; j < THREAD_COUNT; j++) {
                performanceExecutor.execute(() -> {
                    try (SqlSession session = sqlSessionFactory.openSession()) {
                        // Perform a dummy operation (e.g., select 1)
                        session.selectOne("selectOne");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        // Shutdown the executor
        performanceExecutor.shutdown();
        try {
            performanceExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Record the end time and calculate the performance
        long endTime = System.nanoTime();
        double performanceTime = (endTime - startTime) / 1e9;
        System.out.println("Performance Time: " + performanceTime + " seconds");
    }
}