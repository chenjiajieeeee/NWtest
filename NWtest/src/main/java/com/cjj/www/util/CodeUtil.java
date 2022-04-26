package com.cjj.www.util;

import java.util.UUID;

public class CodeUtil {
    //生成唯一激活码

    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
