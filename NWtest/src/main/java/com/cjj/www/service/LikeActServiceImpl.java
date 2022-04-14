package com.cjj.www.service;

import com.cjj.www.dao.LikeActDao;
import com.cjj.www.dao.LikeActDaoImpl;
import com.cjj.www.dao.NoteDao;
import com.cjj.www.dao.NoteDaoImpl;
import com.cjj.www.pojo.Like;
import com.cjj.www.pojo.Note;

import java.util.ArrayList;
import java.util.List;

public class LikeActServiceImpl implements LikeActService{

    @Override
    public boolean judgeLikeOrNot(Integer noteId, Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        return likeActDao.judgeLikeOrNot(noteId, userId);
    }

    @Override
    public boolean LikeAct(Integer noteId, Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        boolean check = likeActDao.judgeLikeOrNot(noteId, userId);
        if(check){
            Like like=new Like();
            like.setNoteId(noteId);
            like.setUserId(userId);
            likeActDao.addLike(like);
            return false;
        }else {
            likeActDao.cancelLike(noteId,userId);
            return true;
        }
    }

    @Override
    public List<Note> queryLikeNoteByUserId(Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        List<Integer> likeNoteIds = likeActDao.queryLikeNoteByUserId(userId);
        List<Note> notes=new ArrayList<>();
        Note note=new Note();
        NoteDao noteDao=new  NoteDaoImpl();
        for (Integer likeNoteId:likeNoteIds){
            note=noteDao.queryNoteByNoteId(likeNoteId);
            notes.add(note);
            note=new Note();
        }
        return notes;
    }
}
