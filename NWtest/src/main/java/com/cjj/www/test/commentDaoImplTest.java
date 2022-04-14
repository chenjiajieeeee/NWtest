package com.cjj.www.test;

import com.cjj.www.dao.CommentDao;
import com.cjj.www.dao.CommentDaoImpl;

import java.util.Set;

public class commentDaoImplTest {
    public static void main(String[] args) {
        CommentDao commentDao=new CommentDaoImpl();
        Set<Integer> likes = commentDao.queryCommentUser(2);
        for (Integer like:likes){
            System.out.println(like);
        }
    }
}
