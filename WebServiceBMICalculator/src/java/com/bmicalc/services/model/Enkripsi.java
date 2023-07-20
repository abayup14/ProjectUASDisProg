/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Andreas Bayu P
 */
public class Enkripsi {

    private String ALGORITHM = "AES";
    private String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private String SECRET_KEY = "DisprogKeren1234"; // Ganti dengan kunci rahasia yang kuat
    private String IV = "UASDisprog123456"; // Ganti dengan vektor inisialisasi yang kuat
    private String plainText;

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String encryptData(String plainText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error di encryptData : " + e);
        }
        return null;
    }
    
    public String decryptData(String encodedText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] encryptedBytes = Base64.getDecoder().decode(encodedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error di decryptdata : " + e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        Enkripsi en = new Enkripsi();
        String data = en.encryptData("baya");
        System.out.println(data);
        String dec = en.decryptData(data);
        System.out.println(dec);
    }
}
