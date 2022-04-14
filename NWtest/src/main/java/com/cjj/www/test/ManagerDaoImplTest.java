package com.cjj.www.test;

import com.cjj.www.dao.ManagerDao;
import com.cjj.www.dao.ManagerDaoImpl;
import com.cjj.www.pojo.UserStatus;

import java.util.List;

public class ManagerDaoImplTest {
    public static void main(String[] args) {
        ManagerDao managerDao=new ManagerDaoImpl();
        List<UserStatus> userStatuses = managerDao.queryZoomStatus();
        for (UserStatus status:userStatuses){
            System.out.println(status.getUserId());
            System.out.println(status.getGameZoomStatus());
            System.out.println(status.getFoodStatus());
            System.out.println(status.getCartoonStatus());
            System.out.println(status.getStudyStatus());
            System.out.println(status.getItStatus());
        }

    }
}
