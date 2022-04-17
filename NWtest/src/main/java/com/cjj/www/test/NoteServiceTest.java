package com.cjj.www.test;

import com.cjj.www.pojo.Note;
import com.cjj.www.service.NoteService;
import com.cjj.www.service.NoteServiceImpl;

import java.util.List;

public class NoteServiceTest {
    public static void main(String[] args) {
        NoteService noteService=new NoteServiceImpl();
        List<Note> notes = noteService.queryNote();
        List<Note> notes1 = noteService.sortNote(notes, "按热度排序");
        for (Note note:notes1){
            System.out.println(note.getBrowse()+"   "+note.getId());
        }
    }
}
