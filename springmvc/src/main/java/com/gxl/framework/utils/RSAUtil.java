/**
 * 
 */
package com.gxl.framework.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ResourceBundle;

import javax.crypto.Cipher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 移动端加解密工具类
 * 
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    private static String sTransform = "RSA";

    /*
     * 加密或解密数据的通用方法
     * 
     * @param srcData 待处理的数据
     * 
     * @param key 公钥或者私钥
     * 
     * @param mode 指定是加密还是解密，值为Cipher.ENCRYPT_MODE或者Cipher.DECRYPT_MODE
     * 
     */
    private static byte[] processData(byte[] srcData, Key key, int mode) throws Exception {

        // 用来保存处理结果
        byte[] resultBytes = null;

        try {

            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance(sTransform);
            // 初始化Cipher，mode指定是加密还是解密，key为公钥或私钥
            cipher.init(mode, key);
            // 处理数据
            resultBytes = cipher.doFinal(srcData);

        } catch (Exception e) {
            throw e;
        }

        return resultBytes;
    }

    /*
     * 使用公钥加密数据，结果用Base64转码
     */
    public static String encryptDataByPublicKey(byte[] srcData, PublicKey publicKey) throws Exception {

        byte[] resultBytes = processData(srcData, publicKey, Cipher.ENCRYPT_MODE);

        return byte2Base64(resultBytes);

    }

    /*
     * 使用私钥解密，返回解码数据
     */
    public static byte[] decryptDataByPrivate(String encryptedData, PrivateKey privateKey) throws Exception {

        byte[] bytes = base642Byte(encryptedData);

        return processData(bytes, privateKey, Cipher.DECRYPT_MODE);
    }

    /*
     * 使用私钥进行解密，解密数据转换为字符串，使用utf-8编码格式
     */
    public static String decryptedToStrByPrivate(String encryptedData, PrivateKey privateKey) throws Exception {
        return new String(decryptDataByPrivate(encryptedData, privateKey));
    }

    /*
     * 使用私钥解密，解密数据转换为字符串，并指定字符集
     */
    public static String decryptedToStrByPrivate(String encryptedData, PrivateKey privateKey, String charset) throws Exception {
        try {

            return new String(decryptDataByPrivate(encryptedData, privateKey), charset);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * 使用私钥加密，结果用Base64转码
     */
    public static String encryptDataByPrivateKey(byte[] srcData, PrivateKey privateKey) throws Exception {

        byte[] resultBytes = processData(srcData, privateKey, Cipher.ENCRYPT_MODE);

        return byte2Base64(resultBytes);
    }

    /*
     * 使用公钥解密，返回解密数据
     */

    public static byte[] decryptDataByPublicKey(String encryptedData, PublicKey publicKey) throws Exception {

        byte[] bytes = base642Byte(encryptedData);
        return processData(bytes, publicKey, Cipher.DECRYPT_MODE);

    }

    /*
     * 使用公钥解密，结果转换为字符串，使用默认字符集utf-8
     */
    public static String decryptedToStrByPublicKey(String encryptedData, PublicKey publicKey) throws Exception {
        return new String(decryptDataByPublicKey(encryptedData, publicKey));
    }

    /*
     * 使用公钥解密，结果转换为字符串，使用指定字符集
     */

    public static String decryptedToStrByPublicKey(String encryptedData, PublicKey publicKey, String charset) throws Exception {
        try {

            return new String(decryptDataByPublicKey(encryptedData, publicKey), charset);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * 将字符串形式的公钥转换为公钥对象
     */

    public static PublicKey keyStrToPublicKey(String publicKeyStr) {

        PublicKey publicKey = null;

        try {
            byte[] keyBytes = base642Byte(publicKeyStr);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(sTransform);

            publicKey = keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;

    }

    /*
     * 将字符串形式的私钥，转换为私钥对象
     */

    public static PrivateKey keyStrToPrivateKey(String privateKeyStr) {

        PrivateKey privateKey = null;

        try {
            byte[] keyBytes = base642Byte(privateKeyStr);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(sTransform);

            privateKey = keyFactory.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKey;

    }

    // 字节数组转Base64编码
    public static String byte2Base64(byte[] bytes) {

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    // Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//        RSAUtil u = new RSAUtil();

        String publicKeyStr = FileUtil.readFileByBytes(ResourceBundle.getBundle(WebConstants.PROPERTIES_FILE_NAME).getString(WebConstants.RSA_PUBLIC_KEY_PATH));
        String privateKeyStr = FileUtil.readFileByBytes(ResourceBundle.getBundle(WebConstants.PROPERTIES_FILE_NAME).getString(WebConstants.RSA_PRIVATE_KEY_PATH));

        // 获取公钥
        PublicKey publicKey = RSAUtil.keyStrToPublicKey(publicKeyStr);
        // 获取私钥
        PrivateKey privateKey = RSAUtil.keyStrToPrivateKey(privateKeyStr);

        // 需要加密的数据
        String public_encry_original_data = "成都信息工程大学";

        // 公钥加密结果
        String publicEncryptedResult = RSAUtil.encryptDataByPublicKey(public_encry_original_data.getBytes(), publicKey);
        // 私钥解密结果
        String privateDecryptedResult = RSAUtil.decryptedToStrByPrivate(publicEncryptedResult, privateKey);

        logger.debug("公钥加密私钥解密原文:" + public_encry_original_data + "\n" + "公钥加密结果:" + publicEncryptedResult + "\n" + "私钥解密结果:" + privateDecryptedResult);

        // 需要加密的数据
        String private_encry_original_data = "CUIT";

        // 私钥加密结果
        String privateEncryptedResult = RSAUtil.encryptDataByPrivateKey(private_encry_original_data.getBytes(), privateKey);
        // 公钥解密结果
        String publicDecryptedResult = RSAUtil.decryptedToStrByPublicKey(privateEncryptedResult, publicKey);

        logger.debug("私钥加密公钥解密原文：" + private_encry_original_data + "\n" + "私钥加密结果:" + privateEncryptedResult + "\n" + "公钥解密结果:" + publicDecryptedResult);

    }

}
