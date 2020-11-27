package com.company;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Descifrar {
    public SecretKey keyGeneration(int keySize) {
        SecretKey secretKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(keySize);
                secretKey = keyGenerator.generateKey();

            } catch (NoSuchAlgorithmException ex) {
                System.err.println("ERROR AL GENERAR: No disponible.");
            }
        }
        return secretKey;
    }
    public SecretKey passwordKeyGeneration(String texto, int keySize) {
        SecretKey secretKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                byte[] data = texto.getBytes("UTF-8");
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] hash = messageDigest.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                secretKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error al generar la clave:" + ex);
            }
        }
        return secretKey;
    }
}