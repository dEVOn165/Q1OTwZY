// 代码生成时间: 2025-09-01 02:17:47
package service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pojo.User;
import java.util.List;

// 数据模型设计
@Mapper
public interface UserService {

    /**
     * 查询所有用户信息
     * @return 用户信息列表
     */
    @Select("SELECT * FROM users")
    List<User> findAllUsers();

    /**
     * 根据ID查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM users WHERE id=#{userId}")
    User findUserById(int userId);

    /**
     * 添加用户信息
     * @param user 用户信息
     * @return 插入结果
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 更新结果
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return 删除结果
     */
    int deleteUser(int userId);

    // 可以根据需要添加更多方法
}
