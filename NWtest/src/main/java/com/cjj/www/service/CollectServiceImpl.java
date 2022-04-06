package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.Collect;
import com.cjj.www.pojo.Note;

import java.util.ArrayList;
import java.util.List;

public class CollectServiceImpl implements CollectService{
    @Override
    public boolean collectAct(Integer noteId, Integer userId) {
        CollectDao collectDao=new CollectDaoImpl();
        boolean check = collectDao.judgeCollectOrNot(noteId, userId);
        if(check){
            Collect collect=new Collect();
            collect.setNoteId(noteId);
            collect.setUserId(userId);
            collectDao.addCollect(collect);
            return true;
        }else {
            collectDao.cancelCollect(noteId,userId);
            return false;
        }
    }

    @Override
    public List<Note> queryCollectNoteByUserId(Integer userId) {
        CollectDao collectDao=new CollectDaoImpl();
        List<Integer> collectNoteIds = collectDao.queryCollectNoteByUserId(userId);
        List<Note> notes=new ArrayList<>();
        Note note=new Note();
        NoteDao noteDao=new  NoteDaoImpl();
        for (Integer collectNoteId:collectNoteIds){
            note=noteDao.queryNoteByNoteId(collectNoteId);
            notes.add(note);
            note=new Note();
        }
        return notes;
    }
}
