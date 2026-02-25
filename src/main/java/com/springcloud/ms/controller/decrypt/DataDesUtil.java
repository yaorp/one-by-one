package com.springcloud.ms.controller.decrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * 解析数据库中的加密工具类
 */
public class DataDesUtil {

    /** 对称加解密DES密钥Key */
    public final static String KEY = "huaqinjiaotongtcjgapps";

    private Cipher mEncryptCipher = null;
    private Cipher mDecryptCipher = null;

    private Cipher mEncryptCipherByNoPadding = null;
    private Cipher mDecryptCipherByNoPadding = null;

    private static DataDesUtil instance = null;

    private DataDesUtil() throws Exception {
        // 初始化加密和解密密码提供类
        mEncryptCipher = Cipher.getInstance("DES");
        mEncryptCipher.init(Cipher.ENCRYPT_MODE, getKey(KEY.getBytes(Charset.defaultCharset())));
        mDecryptCipher = Cipher.getInstance("DES");
        mDecryptCipher.init(Cipher.DECRYPT_MODE, getKey(KEY.getBytes(Charset.defaultCharset())));

        mEncryptCipherByNoPadding = Cipher.getInstance("DES/ECB/NoPadding");
        mEncryptCipherByNoPadding.init(Cipher.ENCRYPT_MODE, getKey(KEY.getBytes(Charset.defaultCharset())));
        mDecryptCipherByNoPadding = Cipher.getInstance("DES/ECB/NoPadding");
        mDecryptCipherByNoPadding.init(Cipher.DECRYPT_MODE, getKey(KEY.getBytes(Charset.defaultCharset())));
    }

    public static DataDesUtil getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        return getNewInstance();
    }

    private synchronized static DataDesUtil getNewInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        instance = new DataDesUtil();
        return instance;
    }

    // ****** 加密 ******

    /**
     * 对 字符串 加密
     */
    public String encrypt(String strIn) throws Exception {
        return byte2HexStr(encrypt(strIn.getBytes(Charset.defaultCharset())));
    }

    /**
     * 对 字节数组 加密
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return mEncryptCipher.doFinal(arrB);
    }

    // ****** 解密 ******

    /**
     * 解密 字符串
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2Byte(strIn)));
    }

    /**
     * 解密 字节数组
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        byte[] result = null;
        try {
            result = mDecryptCipher.doFinal(arrB);
        }
        // 推送方加密数据时，覆盖使用PKCS5Padding、NoPadding的方式进行解密，文档中没有提及这个问题, 默认先使用PKCS5Padding
        catch (BadPaddingException badPaddingException) {
            result = mDecryptCipherByNoPadding.doFinal(arrB);
        }
        return result;
    }

    /**
     * 解密用的密钥（字节数组）长度必须为8个字节否则返回null, 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     */
    private static Key getKey(byte[] arrBTmp) {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    }

    /**
     * HEX转码 String to Byte
     */
    public static byte[] hexStr2Byte(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes(Charset.defaultCharset());
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * HEX转码 Byte to String
     */
    public static String byte2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
         System.out.println(DataDesUtil.getInstance().encrypt("340121199212243714"));
//         System.out.println(DataDesUtil.getInstance().decrypt("83e235990bcd1aee9740522b6fc44a8a"));
        // System.out.println(DataDesUtil.getInstance().decrypt("067ea2a3b021dc8e9a9ab8d903521a8d83f69fa920cfcc1e"));
        // System.out.println(DataDesUtil.getInstance().encrypt("罗志鹏"));
        // System.out.println(DataDesUtil.getInstance().encrypt("340123199212053614"));
        // System.out.println(DataDesUtil.getInstance().encrypt("17602156964"));
        // System.out.println(DataDesUtil.getInstance().decrypt("b8a4537934c470ee"));
        // System.out.println(DataDesUtil.getInstance().encrypt("张慧明"));
//        System.out.println(1123);
    }
}
