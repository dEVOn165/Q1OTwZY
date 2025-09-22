// 代码生成时间: 2025-09-22 12:49:18
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class UserPermissionManager {

    private SqlSessionFactory sqlSessionFactory;

    public UserPermissionManager() {
        try {
            // Configure MyBatis SqlSessionFactory
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new user permission
    public void addPermission(String username, String permission) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // MyBatis Mapper interface
            PermissionMapper mapper = session.getMapper(PermissionMapper.class);
            mapper.addPermission(username, permission);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to remove a user permission
    public void removePermission(String username, String permission) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PermissionMapper mapper = session.getMapper(PermissionMapper.class);
            mapper.removePermission(username, permission);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to query user permissions
    public List<String> queryPermissions(String username) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PermissionMapper mapper = session.getMapper(PermissionMapper.class);
            return mapper.queryPermissions(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inner interface for MyBatis Mapper
    public interface PermissionMapper {
        void addPermission(String username, String permission);
        void removePermission(String username, String permission);
        List<String> queryPermissions(String username);
    }
}
