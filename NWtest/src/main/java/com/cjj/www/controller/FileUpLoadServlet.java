package com.cjj.www.controller;


import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;
import com.cjj.www.service.NoteService;
import com.cjj.www.service.NoteServiceImpl;
import com.cjj.www.util.WebUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FileUpLoadServlet")
public class FileUpLoadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先判断文件是否为多段数据
        if(ServletFileUpload.isMultipartContent(req)){
            List<String> data=new ArrayList<>();
            //创建对应的实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于上传文件的数据类
            ServletFileUpload fileUpload=new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = fileUpload.parseRequest(req);
                for (FileItem fileItem:fileItems){
                    if(fileItem.isFormField()){
                        //true为普通表单项
                        String string = fileItem.getString("UTF-8");
                        data.add(string);
                    }else {
                        String name = fileItem.getName();
                        String baseUrl="http://localhost:8080/nw/note/notePicture/"+name;
                        fileItem.write(new File("D:\\NWtest\\src\\main\\webapp\\note\\notePicture\\"+name));
                        /*
                        将该地址存到数据库里面
                         */
                        NoteService noteService=new NoteServiceImpl();
                        boolean result = noteService.insertNotePicture(baseUrl, WebUtil.toInteger(data.get(0)));
                        System.out.println(result);
                    }
                }
                Integer noteId=WebUtil.toInteger(data.get(0));
                NoteService noteService=new NoteServiceImpl();
                Note note = noteService.queryNoteByNoteId(noteId);
                req.setAttribute("id",WebUtil.toInteger(data.get(0)));
                req.setAttribute("username",data.get(1));
                req.setAttribute("password",data.get(2));
                req.setAttribute("main",data.get(3));
                req.setAttribute("title",data.get(4));
                req.setAttribute("zoom",data.get(5));
                String url=note.getNotePictureUrl();
                req.setAttribute("url",url);
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                req.setAttribute("tags",tags);
                req.setAttribute("updatePictureMsg","添加图片成功！再次返回个人主页即可看见哦！");
                req.getRequestDispatcher("/User/page/updateNote.jsp").forward(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
