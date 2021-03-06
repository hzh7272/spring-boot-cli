package com.base.common.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Des加密工具
 * @author hzh 2018/8/2 13:28
 */
public class DesUtils {

    private final static String DES = "DES";
    private final static String ENCODE = "GBK";
    public final static String defaultKey = "H4ehXYFG";

    /**
     * 根据键值进行加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) {
        try {
            return encrypt(data, defaultKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据键值进行解密
     *
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) {
        if (data == null) {
            return null;
        }
        try {
            return decrypt(data, defaultKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据键值进行加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
        return Base64.getEncoder().encodeToString(bt);
    }

    /**
     * 根据键值进行解密
     *
     * @param data
     * @param key
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        if (data == null) {
            return null;
        }
        try {
            byte[] buf = Base64.getDecoder().decode(data);
            byte[] bt = decrypt(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据键值进行加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);
    }
}
