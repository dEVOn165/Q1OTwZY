// 代码生成时间: 2025-08-27 10:41:11
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvBatchProcessor {

    private static final String CONFIG_FILE = "mybatis-config.xml";
    private static final String MAPPER_FILE = "csvMapper.xml";
    private static final String INPUT_CSV = "input.csv";
    private static final String OUTPUT_TXT = "output.txt";
    private static final int BATCH_SIZE = 100;

    // Method to process the CSV file in batches
    public void processCsvFile() {
        try (SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader(CONFIG_FILE), MAPPER_FILE, "CSVMapper")) {

            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Start a batch operation
                session.startBatch();

                // Read CSV file and process in batches
                List<String[]> batch = new ArrayList<>();
                List<String[]> allData = new ArrayList<>();
                try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        allData.add(data);
                        if (allData.size() >= BATCH_SIZE) {
                            batch.addAll(allData);
                            processBatch(session, batch);
                            allData.clear();
                        }
                    }
                    // Process any remaining data
                    if (!allData.isEmpty()) {
                        batch.addAll(allData);
                        processBatch(session, batch);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error reading CSV file", e);
                }

                // Execute batch operations
                int count = session.commit(true);
                System.out.println("Total records processed: " + count);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }

    // Method to process a batch of data
    private void processBatch(SqlSession session, List<String[]> batch) {
        for (String[] data : batch) {
            try {
                // Call MyBatis mapper method to process each data row
                session.getMapper(CsvMapper.class).insertData(data);
            } catch (Exception e) {
                throw new RuntimeException("Error processing batch", e);
            }
        }
    }

    // Method to write processed data to output file
    private void writeOutputFile(List<String[]> allData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_TXT))) {
            for (String[] data : allData) {
                writer.write(String.join(",", data));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to output file", e);
        }
    }

    // Main method to run the CSV batch processor
    public static void main(String[] args) {
        CsvBatchProcessor processor = new CsvBatchProcessor();
        processor.processCsvFile();
    }
}
