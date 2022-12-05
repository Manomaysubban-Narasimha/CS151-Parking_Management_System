package com.project.CS151Parking_Management_System;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
* Makes use of the PBKDF2 algorithm. More info here: https://www.baeldung.com/java-password-hashing
* Makes use of Simple Factory
*/
public class SecurePasswordHasher
{
   String getHashedPassword(String password)
   {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory;
        
      try {
         factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  // Simple Factory
         byte[] hash = factory.generateSecret(spec).getEncoded();
         return toHexString(hash);
      } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
         e.printStackTrace();
      }
      return null;
   }

   String toHexString(byte[] hash)
   {
       // Convert byte array into signum representation
       BigInteger number = new BigInteger(1, hash);

       // Convert message digest into hex value
       StringBuilder hexString = new StringBuilder(number.toString(16));

       // Pad with leading zeros
       while (hexString.length() < 64)
       {
           hexString.insert(0, '0');
       }
       return hexString.toString();
   }
}
