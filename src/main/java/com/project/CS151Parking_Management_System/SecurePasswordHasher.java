package com.project.CS151Parking_Management_System;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
* Makes use of the PBKDF2 algorithm. More info here: https://www.baeldung.com/java-password-hashing
* Makes use of Simple Factory
*/
public class SecurePasswordHasher
{
    private static final String SALT = "}!'&@%#),*&^=+-_\\|~`fclsiyh<";
    static final int NUM_OF_ASCII_CHARS = 128;

    String getHashedPassword(String password) throws NoSuchAlgorithmException
    {

        StringBuilder saltedAndPeppered = new StringBuilder();
        saltedAndPeppered.append(getSaltedPassword(password));
        saltedAndPeppered.append(getPepper());
        return toHexString(getSHA(saltedAndPeppered.toString()));
    }

    char getPepper()
    {
        Random rand = new Random();
        return (char)rand.nextInt(NUM_OF_ASCII_CHARS);
    }

    String getSaltedPassword(String password)
    {
        StringBuilder saltedPassword = new StringBuilder();
        saltedPassword.append(SALT.substring(SALT.length()/2));
        saltedPassword.append(password);
        saltedPassword.append(SALT.substring(0, SALT.length()/2));
        return saltedPassword.toString();
    }

    boolean passwordsMatch(String userPassword, String dbPassword) throws NoSuchAlgorithmException
    {
        StringBuilder compare = new StringBuilder(getSaltedPassword(userPassword));
        // trying to figure out which pepper suits
        for(int i = 0; i < NUM_OF_ASCII_CHARS; i ++)
        {
            compare.append((char)i);
            if(toHexString(getSHA(compare.toString())).equals(dbPassword))
                return true;
            compare.deleteCharAt(compare.lastIndexOf(((char)i) + ""));
        }
        return false;
    }

    private byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA3-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private String toHexString(byte[] hash)
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
