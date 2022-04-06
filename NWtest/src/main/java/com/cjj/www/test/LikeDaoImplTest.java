package com.cjj.www.test;

import com.cjj.www.dao.LikeActDao;
import com.cjj.www.dao.LikeActDaoImpl;

import java.util.List;

public class LikeDaoImplTest {
    public static void main(String[] args) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        List<Integer> integers = likeActDao.queryLikeNoteByUserId(2);
        for (Integer integer:integers){
            System.out.println(integer);
        }

    }
}
