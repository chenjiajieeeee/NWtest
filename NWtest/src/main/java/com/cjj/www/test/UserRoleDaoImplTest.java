package com.cjj.www.test;

import com.cjj.www.dao.UserRoleDao;
import com.cjj.www.dao.UserRoleDaoImpl;
import com.cjj.www.pojo.User;

public class UserRoleDaoImplTest {
    public static void main(String[] args) {
        UserRoleDao userRoleDao=new UserRoleDaoImpl();
        boolean b = userRoleDao.updatePassword("上海滩许van强", "147258369", "123456789");
        System.out.println(b);
    }
}
