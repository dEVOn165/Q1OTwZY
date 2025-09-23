// 代码生成时间: 2025-09-23 19:10:42
// RandomNumberGenerator.java
// 该类是一个简单的随机数生成器，使用Java标准库中的Random类。

import java.util.Random;

public class RandomNumberGenerator {

    // 构造函数
    public RandomNumberGenerator() {
        random = new Random();
    }

    // 生成一个指定范围的随机整数
    public int generateRandomInt(int min, int max) {
        if (min > max) {
            // 错误处理：最小值不能大于最大值
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        return random.nextInt((max - min) + 1) + min;
    }

    // 生成一个指定范围的随机浮点数
    public double generateRandomDouble(double min, double max) {
        if (min > max) {
            // 错误处理：最小值不能大于最大值
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        return min + (max - min) * random.nextDouble();
    }

    // 私有变量，用于生成随机数
    private Random random;

    public static void main(String[] args) {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        try {
            int randomInt = rng.generateRandomInt(1, 10);
            System.out.println("生成的随机整数: " + randomInt);

            double randomDouble = rng.generateRandomDouble(0.0, 1.0);
            System.out.println("生成的随机浮点数: " + randomDouble);
        } catch (IllegalArgumentException e) {
            System.err.println("错误: " + e.getMessage());
        }
    }
}
