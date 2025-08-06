// 代码生成时间: 2025-08-06 18:15:04
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.util.List;
import java.util.ArrayList;

// 图片尺寸批量调整器
public class ImageResizer {

    // 读取MyBatis配置文件
    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    // 调整图片尺寸
    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return resizedImage;
    }

    // 批量调整文件夹内图片尺寸
    public static void resizeImagesInFolder(String folderPath, int targetWidth, int targetHeight) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null) {
            throw new IllegalArgumentException("The provided folder path is not a directory or contains no files.");
        }

        for (File file : files) {
            if (file.isFile() && isImageFile(file)) {
                try {
                    BufferedImage originalImage = ImageIO.read(file);
                    BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);
                    File outputfile = new File(file.getParent() + "/resized_" + file.getName());
                    ImageIO.write(resizedImage, "jpg", outputfile);
                } catch (IOException e) {
                    System.err.println("Error resizing image: " + file.getName());
                }
            }
        }
    }

    // 检查文件是否为图片
    private static boolean isImageFile(File file) {
        String fileName = file.getName();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }

    // 主方法，程序入口
    public static void main(String[] args) {
        try {
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
            // 假设有一个MyBatis的mapper接口
            // YourMapper mapper = sqlSessionFactory.openSession().getMapper(YourMapper.class);
            // 执行数据库操作
            // ...

            // 调整图片尺寸示例
            String folderPath = "/path/to/your/image/folder";
            int targetWidth = 800;
            int targetHeight = 600;
            resizeImagesInFolder(folderPath, targetWidth, targetHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
