// 代码生成时间: 2025-08-14 18:09:57
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class ImageResizer {
    // 定义目标尺寸
    private int targetWidth;
    private int targetHeight;

    // 构造函数，设置目标尺寸
    public ImageResizer(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    // 批量调整图片尺寸
    public void resizeImages(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a valid directory.");
        }

        File[] files = directory.listFiles((file) -> file.isFile() &&
                (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")));

        if (files == null) {
            throw new IOException("Failed to read directory.");
        }

        for (File file : files) {
            BufferedImage sourceImage = ImageIO.read(file);
            if (sourceImage == null) {
                throw new IOException("Failed to read image file: " + file.getName());
            }

            // 调整图片尺寸
            BufferedImage resizedImage = resizeImage(sourceImage);

            // 保存调整后的图片
            String fileName = file.getName().substring(0, file.getName().lastIndexOf('.')) + "_resized" + file.getName().substring(file.getName().lastIndexOf('.'));
            File outputFile = new File(file.getParent(), fileName);
            ImageIO.write(resizedImage, "PNG", outputFile);
        }
    }

    // 调整图片尺寸的方法
    private BufferedImage resizeImage(BufferedImage originalImage) {
        Image tmp = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resizedImage;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        try {
            ImageResizer resizer = new ImageResizer(800, 600); // 调整尺寸为800x600
            resizer.resizeImages("path/to/your/image/directory"); // 替换为实际的图片目录路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
