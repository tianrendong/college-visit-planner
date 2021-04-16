package edu.brown.cs.termproject.iotools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;


/**
 * Utility class for encryption.
 */
public final class Encryption {

  private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
  private static final String SALT = "ssshhhhhhhhhhh!!!!";
  private static final int ITERATION_COUNT = 65536;
  private static final int KEY_LENGTH = 256;
  private static SecretKeyFactory factory;
  private static IvParameterSpec ivspec;
  private static KeySpec spec;
  private static SecretKeySpec secretKey;

  private Encryption() { }

  static {
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      ivspec = new IvParameterSpec(iv);
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
      SecretKey tmp = factory.generateSecret(spec);
      secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
  }

  /**
   * Encrypts the string.
   * @param strToEncrypt string to be encrypted.
   * @return encrypted version of the string.
   */
  public static String encrypt(String strToEncrypt) {
    try {
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec sk = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, sk, ivspec);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("ERROR: during encryption");
    }
    return null;
  }

  /**
   * Verify whether a string is an encryption of another string.
   * @param input Input string.
   * @param encrypted Encrypted version string.
   * @return whether encrypted is the correct encryption for input.
   * @throws InvalidKeySpecException when no valid key.
   * @throws NoSuchAlgorithmException when no encryption algorithm.
   * @throws UnsupportedEncodingException when encoding is not supported.
   */
  public static boolean verify(String input, String encrypted)
      throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
    return input.equals(decrypt(encrypted));
  }

  /**
   * Decrypts a string.
   * @param strToDecrypt the string to decrypt.
   * @return decrypted string of an encrypted.
   */
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

