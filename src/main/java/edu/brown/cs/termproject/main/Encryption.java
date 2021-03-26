package edu.brown.cs.termproject.main;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Encryption {

  public static byte[] encrypt(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = factory.generateSecret(spec).getEncoded();
    return hash;
  }

  public static boolean verifyPassword(String enteredPassword, byte[] correctHash)
      throws InvalidKeySpecException, NoSuchAlgorithmException {
    byte[] hash = encrypt(enteredPassword);
    return Arrays.equals(hash, correctHash);
  }
}

