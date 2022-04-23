package com.cjj.www.test;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.User;

public class UserDaoImplTest {
    public static void main(String[] args) {
        UserDao ud=new UserDaoImpl();
<<<<<<< HEAD
<<<<<<< HEAD
        User user1 = ud.queryUserByUserName("上海滩van强哥");
        System.out.println(user1.getId());
=======
        System.out.println(ud.countUser());
>>>>>>> 8d84cdf (eigth)
=======
        System.out.println(ud.countUser());
>>>>>>> 983e94e (ninth)

    }
}
