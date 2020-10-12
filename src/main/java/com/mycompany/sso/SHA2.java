package com.mycompany.sso;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class SHA2 {
    public static void main(String[] args) {
        System.out.println(getSHA256("1234", new Random().nextInt(100000)));
        System.out.println(getSHA256("1234", new Random().nextInt(100000)));
    }
    
    public static String getSHA256(String password, int salt) {
        String hash = null;
        System.out.println(password + ", " + salt);
        password += salt;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(password.getBytes("utf-8"));
            hash = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }
}
