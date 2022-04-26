package com.cjj.www.util;


import java.io.IOException;
import java.util.logging.*;

public class LogUtil {
    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    private FileHandler fileHandler;

    public  Logger WriteLog(String name) throws IOException {
        // 1.创建日志记录器对象
        Logger logger = Logger.getLogger(name);

        // b.创建handler对象
        ConsoleHandler handler = new ConsoleHandler();

        // c.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // d.进行关联
        handler.setFormatter(simpleFormatter);
        logger.addHandler(handler);
        // e.设置日志级别
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);

        // 二、输出到日志文件（前提要存在此文件）
        FileHandler fileHandler = new FileHandler("D:\\Log\\admin.log",true);
        this.fileHandler=fileHandler;
        // 进行关联
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        return logger;
    }
}
