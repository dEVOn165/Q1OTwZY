// 代码生成时间: 2025-10-13 18:24:53
package com.example.security;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * TLS/SSL 证书管理器，用于加载和管理证书。
 * 使用 MyBatis 框架进行数据操作。
 */
public class TlsCertificateManager {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 构造函数，初始化 SQL 会话工厂。
     * @param sqlSessionFactory SQL 会话工厂
     */
    public TlsCertificateManager(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 从 KeyStore 中加载证书。
     * @param keyStorePath 密钥库文件的路径
     * @param keyStorePassword 密钥库密码
     * @return KeyStore 实例
     * @throws Exception 任何异常
     */
    public KeyStore loadKeyStore(String keyStorePath, String keyStorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream is = new FileInputStream(keyStorePath)) {
            keyStore.load(is, keyStorePassword.toCharArray());
        } catch (Exception e) {
            throw new Exception("Failed to load keystore", e);
        }
        return keyStore;
    }

    /**
     * 获取特定的证书。
     * @param alias 证书的别名
     * @param keyStore KeyStore 实例
     * @return X509Certificate 证书
     * @throws Exception 任何异常
     */
    public X509Certificate getCertificate(String alias, KeyStore keyStore) throws Exception {
        if (keyStore.containsAlias(alias)) {
            return (X509Certificate) keyStore.getCertificate(alias);
        } else {
            throw new Exception("Certificate with alias does not exist");
        }
    }

    /**
     * 获取特定的私钥。
     * @param alias 证书的别名
     * @param keyStore KeyStore 实例
     * @param keyPassword 私钥密码
     * @return PrivateKey 私钥
     * @throws Exception 任何异常
     */
    public PrivateKey getPrivateKey(String alias, KeyStore keyStore, String keyPassword) throws Exception {
        if (keyStore.containsAlias(alias)) {
            return (PrivateKey) keyStore.getKey(alias, keyPassword.toCharArray());
        } else {
            throw new Exception("Private key with alias does not exist");
        }
    }

    /**
     * 获取证书链。
     * @param alias 证书的别名
     * @param keyStore KeyStore 实例
     * @return X509Certificate[] 证书链
     * @throws Exception 任何异常
     */
    public X509Certificate[] getCertificateChain(String alias, KeyStore keyStore) throws Exception {
        if (keyStore.containsAlias(alias)) {
            X509Certificate[] chain = keyStore.getCertificateChain(alias);
            if (chain == null) {
                throw new Exception("No certificate chain for alias");
            }
            return chain;
        } else {
            throw new Exception("Certificate with alias does not exist");
        }
    }

    /**
     * 遍历 KeyStore 中的所有证书。
     * @param keyStore KeyStore 实例
     */
    public void listCertificates(KeyStore keyStore) {
        try {
            Enumeration<String> aliasesEnum = keyStore.aliases();
            while (aliasesEnum.hasMoreElements()) {
                String alias = aliasesEnum.nextElement();
                X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
                System.out.println("Certificate alias: " + alias + ", SubjectDN: " + certificate.getSubjectDN().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 这里可以添加更多的方法，例如更新证书、删除证书等。
}
