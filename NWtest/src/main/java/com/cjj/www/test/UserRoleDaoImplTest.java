package com.cjj.www.test;

import com.cjj.www.dao.UserRoleDao;
import com.cjj.www.dao.UserRoleDaoImpl;
import com.cjj.www.pojo.User;
import com.cjj.www.util.Encryption;

public class UserRoleDaoImplTest {


    public static void main(String[] args) {
        UserRoleDao userRoleDao=new UserRoleDaoImpl();
        Encryption encryption=new Encryption();
        String salt = encryption.salt();
        userRoleDao.updatePassword("秋月爱莉","8ca50e33ce9531e2cdeb55c0bb834550",encryption.encryptMD5("1111",salt),salt);

    }
}
