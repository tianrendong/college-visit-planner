package edu.brown.cs.termproject.main;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encryption {

  private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
  private static final String SALT = "ssshhhhhhhhhhh!!!!";
  private static SecretKeyFactory factory;
  private static IvParameterSpec ivspec;
  private static KeySpec spec;
  private static SecretKeySpec secretKey;

  static {
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      ivspec = new IvParameterSpec(iv);
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
  }

  public static String encrypt(String strToEncrypt) {
    try {
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("ERROR: durng encryption");
    }
    return null;
  }

  public static boolean verify(String input, String encrypted)
      throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
    return input.equals(decrypt(encrypted));
  }

  public static String decrypt(String strToDecrypt) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("ERROR: during decryption");
    }
    return null;
  }
}

