package com.springcloud.ms.controller.decrypt;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yaorp
 */
public class AesUtil {
    // 测试环境
    public static String KEY = "tcjgappstcjgapps";
    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static ConcurrentHashMap<String, Cipher> cipherMap = new ConcurrentHashMap();

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr
     *            待解密的base 64 code
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), KEY);
    }

    /**
     * base 64 decode
     *
     * @param base64Code
     *            待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     *             抛出异常
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : DatatypeConverter.parseBase64Binary(base64Code);
    }

    /**
     * AES解密
     *
     * @param encryptBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = getCipherInstance(decryptKey, Cipher.DECRYPT_MODE);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * 获取Cipher实例
     *
     * @param key
     *            Cipher实例名称
     * @param type
     *            Cipher.DECRYPT_MODE or ENCRYPT_MODE
     * @return Cipher实例
     */
    private static Cipher getCipherInstance(String key, int type) throws Exception {

        String mapKey = String.format("%s:%s", key, type);
        if (cipherMap.containsKey(mapKey)) {
            return cipherMap.get(mapKey);
        }

        return getCipherInstanceForSync(key, type);
    }

    /**
     * 同步获取或创建Cipher实例
     *
     * @param key
     *            Cipher实例名称
     * @param type
     *            Cipher.DECRYPT_MODE or ENCRYPT_MODE
     * @return Cipher实例
     */
    private static synchronized Cipher getCipherInstanceForSync(String key, int type) throws Exception {

        String mapKey = String.format("%s:%s", key, type);
        if (cipherMap.containsKey(mapKey)) {
            return cipherMap.get(mapKey);
        }
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(type, new SecretKeySpec(key.getBytes(), "AES"));
        cipherMap.put(mapKey, cipher);
        return cipher;
    }

    /**
     * AES加密为base 64 code
     *
     * @param content
     *            待加密的内容
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content) throws Exception {
        return base64Encode(aesEncryptToBytes(content, KEY));
    }

    /**
     * AES加密
     *
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = getCipherInstance(encryptKey, Cipher.ENCRYPT_MODE);

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * base 64 encode
     *
     * @param bytes
     *            待编码的byte[]
     * @return 编码后的base 64 code
     */
    private static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

}
