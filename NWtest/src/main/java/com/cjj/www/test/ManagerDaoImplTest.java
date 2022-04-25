package com.cjj.www.test;

import com.cjj.www.dao.ManagerDao;
import com.cjj.www.dao.ManagerDaoImpl;
import com.cjj.www.pojo.UserStatus;

import java.util.List;

public class ManagerDaoImplTest {
    public static void main(String[] args) {
        ManagerDao managerDao=new ManagerDaoImpl();
        managerDao.changeZoom(10,"游戏区");
    }
}
