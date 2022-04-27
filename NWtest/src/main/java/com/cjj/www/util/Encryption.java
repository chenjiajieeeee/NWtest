package com.cjj.www.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Encryption {
    //MD5加密
    public String encryptMD5(String info)  {
        //根据MD5算法生成MessageDigest对象
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
