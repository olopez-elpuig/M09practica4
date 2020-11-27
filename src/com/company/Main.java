package com.company;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/oscar/Escritorio/textamagat");
        byte[] txtByte = Files.readAllBytes(path);

        File f = new File("/home/oscar/Escritorio/contraseñas.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br =  new BufferedReader(fr);
        int keysize = 128;
        byte[] decryptedTxt = null;
        while (decryptedTxt == null) {
            String line = br.readLine();
            SecretKey secretKey = Cifrar.passwordKeyGeneration(line, keysize);
            decryptedTxt = Cifrar.decryptData(secretKey, txtByte);
        }

        System.out.println("Texto desencriptado: " + new String(decryptedTxt));




    }

}
