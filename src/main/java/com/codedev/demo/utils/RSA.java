package com.codedev.demo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Slf4j
public class RSA {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    // constructor
    public RSA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            log.info("rsa constructor - privateKey: {}", privateKey);
            publicKey = pair.getPublic();
            log.info("rsa constructor - publicKey: {}", publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String message) throws Exception {
        log.info("\n------ encrypt and encode ------\n");
        log.info("message: {}", message);
        byte[] messageBytes = message.getBytes();
        log.info("messageBytes: {}", messageBytes);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // add algorithm
//        AES/CBC/NoPadding (128)
//        AES/CBC/PKCS5Padding (128)
//        AES/ECB/NoPadding (128)
//        AES/ECB/PKCS5Padding (128)
//        DES/CBC/NoPadding (56)
//        DES/CBC/PKCS5Padding (56)
//        DES/ECB/NoPadding (56)
//        DES/ECB/PKCS5Padding (56)
//        DESede/CBC/NoPadding (168)
//        DESede/CBC/PKCS5Padding (168)
//        DESede/ECB/NoPadding (168)
//        DESede/ECB/PKCS5Padding (168)
//        RSA/ECB/PKCS1Padding (1024, 2048)
//        RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
//        RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // add public key
        byte[] encryptedBytes = cipher.doFinal(messageBytes); // add message
        log.info("encryptedBytes: {}", encryptedBytes);

        return new String(encode(encryptedBytes),"UTF8");
    }

    private byte[] encode(byte[] data) {
        log.info("encoding ......");
        return Base64.getEncoder().encode(data);
        // return Base64.getEncoder().encodeToString(data);
    }

    public String decrypt(String encryptedMessage) throws Exception {
        log.info("\n------ decode and decrypt ------\n");
        log.info("encryptedMessage: {}", encryptedMessage);
        byte[] encryptedBytes = decode(encryptedMessage);
        log.info("encryptedBytes: {}", encryptedBytes);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // add algorithm
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // add private key
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes); // add message
        log.info("decryptedBytes: {}", decryptedBytes);

        return new String(decryptedBytes,"UTF8");
    }

    private byte[] decode(String data) {
        log.info("decoding ......");
        return Base64.getDecoder().decode(data);
    }

}
