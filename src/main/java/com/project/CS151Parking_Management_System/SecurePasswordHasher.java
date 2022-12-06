package com.project.CS151Parking_Management_System;

import java.io.Serializable;
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
 * Securely hashes password using static salt, pepper, and SHA3-256 one-way cryptographic hash function
 * Implemented using the Singleton design pattern that is thread, reflection, and serialization safe
 *
 * Referred: https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0
 */
public class SecurePasswordHasher implements Serializable
{
    private static final String SALT = "}!'&@%#),*&^=+-_\\|~`fclsiyh<";
    static final int NUM_OF_ASCII_CHARS = 128;
    private static volatile SecurePasswordHasher securePasswordHasher;

    private SecurePasswordHasher()
    {
        // Prevent from reflection api
        if(securePasswordHasher != null)
            throw new RuntimeException("Must use the getSecurePasswordHasher() method to get a Secure password hasher");
    }

    public static SecurePasswordHasher getInstance()
    {
        if(securePasswordHasher == null)  // if there doesn't exist an object already, create one
        {
            synchronized(SecurePasswordHasher.class)
            {
                if(securePasswordHasher == null)
                    securePasswordHasher = new SecurePasswordHasher();
            }
        }
        return securePasswordHasher;
    }

    String getHashedPassword(String password) throws NoSuchAlgorithmException
    {

        StringBuilder saltedAndPeppered = new StringBuilder();
        saltedAndPeppered.append(getSaltedPassword(password));
        saltedAndPeppered.append(getPepper());
        return toHexString(getSHA(saltedAndPeppered.toString()));
    }

    private char getPepper()
    {
        Random rand = new Random();
        return (char)rand.nextInt(NUM_OF_ASCII_CHARS);
    }

    private String getSaltedPassword(String password)
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
        boolean passwordMatch = false;
        // trying to figure out which pepper suits
        for(int i = 0; i < NUM_OF_ASCII_CHARS; i ++)
        {
            compare.append((char)i);
            if(toHexString(getSHA(compare.toString())).equals(dbPassword))
                passwordMatch = true;
            compare.deleteCharAt(compare.lastIndexOf(((char)i) + ""));
        }
        return passwordMatch;
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
