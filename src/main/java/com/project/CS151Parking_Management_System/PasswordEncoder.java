package com.project.CS151Parking_Management_System;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import java.util.Random;

/**
 * Securely hashes password using static salt, pepper, and SHA3-256 one-way cryptographic hash function
 * Implemented using the Singleton design pattern that is thread, reflection, and serialization safe
 *
 * Referred: https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0
 */
public class PasswordEncoder implements Serializable
{
    private static volatile PasswordEncoder passwordEncoder;
    private SCryptPasswordEncoder encoder;

    private PasswordEncoder()
    {
        // Prevent from reflection api
        if(passwordEncoder != null)
            throw new RuntimeException("Must use the getSecurePasswordHasher() method to get a Secure password hasher");
        encoder = new SCryptPasswordEncoder();
    }

    public static PasswordEncoder getInstance()
    {
        if(passwordEncoder == null)  // if there doesn't exist an object already, create one
        {
            synchronized(PasswordEncoder.class)
            {
                if(passwordEncoder == null)
                    passwordEncoder = new PasswordEncoder();
            }
        }
        return passwordEncoder;
    }

    String encode(String password) throws NoSuchAlgorithmException
    {
        return encoder.encode(password);
    }
}
