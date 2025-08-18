// 代码生成时间: 2025-08-18 10:27:29
package com.example.hashcalculator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希值计算工具类，用于计算字符串的SHA-256哈希值。
 *
 * @author yourname
 * @version 1.0
 */
public class HashCalculator {

    /**
     * 计算给定字符串的SHA-256哈希值。
     *
     * @param input 需要计算哈希值的字符串
     * @return 字符串的SHA-256哈希值
     * @throws NoSuchAlgorithmException 当没有SHA-256算法时抛出此异常
     */
    public static String calculateSHA256Hash(String input) throws NoSuchAlgorithmException {
        // 获取SHA-256 MessageDigest实例
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // 将输入字符串转换为字节数组
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

        // 更新MessageDigest实例，计算哈希值
        byte[] hashedBytes = digest.digest(inputBytes);

        // 将字节数组转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            hexString.append(String.format("%02x", b));
        }

        // 返回哈希值
        return hexString.toString();
    }

    /**
     * 主方法，用于测试哈希值计算工具。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try {
            String input = "Hello, World!";
            String hash = calculateSHA256Hash(input);
            System.out.println("输入字符串: " + input);
            System.out.println("SHA-256 哈希值: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256算法未找到: " + e.getMessage());
        }
    }
}