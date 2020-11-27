package com.company;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Cifrar {

    public static SecretKey keygenKeyGeneration(String txtKey, int keySize) {
        SecretKey secretKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                KeyGenerator genKey = KeyGenerator.getInstance("AES");
                genKey.init(keySize);
                secretKey = genKey.generateKey();

            } catch (NoSuchAlgorithmException ex) {
                System.err.println("ERROR AL GENERAR: No disponible.");
            }
        }
        return secretKey;
    }
    public static SecretKey passwordKeyGeneration(String text, int keySize) {
        SecretKey secretKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] hash = messageDigest.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                secretKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generando la clave:" + ex);
            }
        }
        return secretKey;
    }
    public static byte[] encryptData(SecretKey secretKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedData =  cipher.doFinal(data);
        } catch (Exception  ex) {
            System.err.println("Error al cifrar los datos: " + ex);
        }
        return encryptedData;
    }
    public static byte[] decryptData(SecretKey secretKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            encryptedData =  cipher.doFinal(data);
        } catch (Exception  ex) {
            System.err.println("Error al descifrar los datos: " + ex);
        }
        return encryptedData;
    }
}