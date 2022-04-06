package com.cjj.www.test;

import com.cjj.www.dao.CollectDao;
import com.cjj.www.dao.CollectDaoImpl;
import com.cjj.www.pojo.Collect;

public class CollectDaoImplTest {
    public static void main(String[] args) {
        Collect collect=new Collect();
        collect.setUserId(1);
        collect.setNoteId(39);
        CollectDao collectDao=new CollectDaoImpl();
        collectDao.addCollect(collect);
        boolean b = collectDao.judgeCollectOrNot(collect.getNoteId(), collect.getUserId());
        System.out.println(b);
    }
}
