package com.cjj.www.test;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.User;

public class UserDaoImplTest {
    public static void main(String[] args) {
        UserDao ud=new UserDaoImpl();
        System.out.println(ud.countUser());

    }
}
