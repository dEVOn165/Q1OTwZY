// 代码生成时间: 2025-08-21 19:25:11
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class APIResponseFormatter {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to format API response
    public static Map<String, Object> formatResponse(int code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);

        return response;
    }

    // Method to get a SqlSession from the factory
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    // Example usage of the APIResponseFormatter
    public static void main(String[] args) {
        try {
            APIResponseFormatter formatter = new APIResponseFormatter();
            SqlSession sqlSession = formatter.getSqlSession();

            // Example data retrieval operation
            // Assuming a method `selectData` exists in the mapper interface
            // Object data = sqlSession.selectOne("namespace.selectData");

            // Format the API response
            Map<String, Object> response = formatter.formatResponse(200, "Success", null); // Replace null with actual data

            // Close the SqlSession
            sqlSession.close();

            // Print the formatted response
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}