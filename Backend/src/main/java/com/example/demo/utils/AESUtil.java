package com.example.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String encrypt(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
