package com.cjj.www.util;


import java.util.logging.Logger;

public class LogUtil {
    //写日志
    public Logger writeLogger(String name){
        Logger logger= Logger.getLogger(name);
        //日记记录输出
        return logger;
    }
}
