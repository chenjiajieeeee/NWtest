package com.cjj.www.test;

import com.cjj.www.util.Encryption;

import java.security.NoSuchAlgorithmException;

public class md5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Encryption encryption=new Encryption();
        String salt = encryption.salt();
        System.out.println(encryption.encryptMD5("1216978720",salt));
        System.out.println(salt);
    }
}
