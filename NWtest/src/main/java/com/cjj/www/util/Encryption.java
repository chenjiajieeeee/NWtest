package com.cjj.www.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Encryption {
    //生成盐
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public String salt() {
        Random random = new Random();
        StringBuilder salt = new StringBuilder(16);
        for (int i = 0; i < salt.capacity(); i++) {
            salt.append(hex[random.nextInt(16)]);
        }
        return salt.toString();
    }

    //MD5加密
    public String encryptMD5(String info,String salt) {
        //根据MD5算法生成MessageDigest对象
        info=info+salt;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte [] srcBytes = info.getBytes();
            //使用srcBytes更新摘要
            md5.update(srcBytes);
            //完成哈希计算，得到result
            return new BigInteger(1,md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }return null;
    }

}
