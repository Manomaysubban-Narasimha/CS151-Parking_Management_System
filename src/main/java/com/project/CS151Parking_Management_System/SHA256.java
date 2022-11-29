package com.project.CS151Parking_Management_System;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* https://www.geeksforgeeks.org/sha-256-hash-in-java/
* https://www.geeksforgeeks.org/difference-between-md5-and-sha1/
* https://www.geeksforgeeks.org/difference-between-sha1-and-sha256/
*/
public class SHA256
{
   public static byte[] getSHA(String input) throws NoSuchAlgorithmException
   {
       // Static getInstance method is called with hashing SHA
       MessageDigest md = MessageDigest.getInstance("SHA-256");

       // digest() method called
       // to calculate message digest of an input
       // and return array of byte
       return md.digest(input.getBytes(StandardCharsets.UTF_8));
   }

   public static String toHexString(byte[] hash)
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
