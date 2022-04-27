package com.cjj.www.test;

import com.cjj.www.util.Encryption;

import java.security.NoSuchAlgorithmException;

public class md5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Encryption encryption=new Encryption();
        System.out.println(encryption.encryptMD5(""));
    }
}
