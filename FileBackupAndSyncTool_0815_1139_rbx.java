// 代码生成时间: 2025-08-15 11:39:04
package com.file.backup.sync.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 文件备份和同步工具
 * 提供文件备份和同步功能，确保数据的一致性和完整性。
 */
@MapperScan("com.file.backup.sync.tool.mapper")
public class FileBackupAndSyncTool {

    private static final String BACKUP_FOLDER = "backup/";
    private SqlSessionFactory sqlSessionFactory;

    public FileBackupAndSyncTool(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 备份文件
     * @param sourceFilePath 文件源路径
     * @param backupFilePath 文件备份路径
     * @throws IOException 文件读写异常
     */
    public void backupFile(String sourceFilePath, String backupFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File backupFile = new File(backupFilePath);

        if (!sourceFile.exists()) {
            throw new IOException("Source file does not exist: " + sourceFilePath);
        }

        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(backupFile)) {
            // 复制文件内容
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new IOException("Error occurred while backing up file: " + e.getMessage(), e);
        }
    }

    /**
     * 同步文件
     * @param sourceFilePath 文件源路径
     * @param targetFilePath 文件目标路径
     * @throws IOException 文件读写异常
     */
    public void syncFile(String sourceFilePath, String targetFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        if (!sourceFile.exists()) {
            throw new IOException("Source file does not exist: " + sourceFilePath);
        }

        try {
            // 复制文件到目标路径
            Files.copy(Paths.get(sourceFilePath), Paths.get(targetFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Error occurred while syncing file: " + e.getMessage(), e);
        }
    }

    /**
     * 获取备份文件夹路径
     * @return 备份文件夹路径
     */
    public String getBackupFolderPath() {
        return BACKUP_FOLDER;
    }

    public static void main(String[] args) {
        // 初始化MyBatis SqlSessionFactory
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource.getReader());

        // 创建文件备份和同步工具实例
        FileBackupAndSyncTool tool = new FileBackupAndSyncTool(sqlSessionFactory);

        try {
            // 备份文件示例
            tool.backupFile("/path/to/source/file.txt", "/path/to/backup/file.txt");

            // 同步文件示例
            tool.syncFile("/path/to/source/file.txt", "/path/to/sync/file.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
