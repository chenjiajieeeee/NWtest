package com.cjj.www.util;

import java.util.UUID;

public class CodeUtil {

    /**
     *
     * @return 生成的激活码
     */
    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
