package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Report;
import com.cjj.www.pojo.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface NoteService {
    /**
     * 用于将笔记封装成list集合输出在首页
     * @return list集合，为笔记对象的集合
     */
    List<Note> queryNote();
    /**
     * 用于将笔记封装成list集合输出在个人的空间
     * @param username 发布了的笔记的用户的用户名
     * @return list 集合，为笔记对象的集合
     */
    List<Note> queryNoteByUsername(String username);
    /**
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     * 用于转移servlet
     */
    void updateNote(HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新笔记内容
     * @param newMain 新的笔记内容
     * @param id 该笔记的id
     * @return ture修改成功，false修改失败
     */
    boolean updateNoteMain(String newMain,Integer id);

    /**
     * 更新笔记标题
     * @param newTitle 新的笔记标题
     * @param id 该笔记的id
     * @return ture为修改成功，false为修改失败
     */
    boolean updateNoteTitle(String newTitle,Integer id);

    /**
     *
     * @param id 需要删除的笔记的id
     * @return ture为删除成功，false为删除失败
     */
    boolean deleteNote(Integer id);

    /**
     * 对应servlet的转移
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void publishNote(HttpServletRequest request,HttpServletResponse response);

    /**
     * 将笔记保存在数据库中
     * @param note 封装好了的笔记对象
     * @param zoomName 用于检验该用户在该区域是否被禁止发布笔记
     * @return “发布成功” “用户被禁用” “某一不为空”
     */
    String saveNote(Note note ,String zoomName);
    Note queryNoteByNoteId(Integer noteId);

    /**
     *
     * @param begin 查询笔记的起始点
     * @param end 查询笔记的结束点
     * @return list集合，为笔记对象
     */
    List<Note> queryNotePaging(Integer begin,Integer end);

    /**
     *
     * @param begin 查询笔记的七十点
     * @param end 查询笔记的结束点
     * @param zoomName 限定查询范围
     * @return list集合，为笔记对象
     */
    List<Note> queryNotePaging(Integer begin,Integer end,String zoomName);

    /**
     *
     * @return 笔记总个数（去除到那些审核中以及被驳回和被管理员删除的笔记）
     */
    Integer queryNoteTotalPage();

    /**
     *
     * @param zoomName 限定查询范围
     * @return 笔记总个数（去除掉那些审核中以及被驳回和被管理员删除的笔记）
     */
    Integer queryNoteTotalPage(String zoomName);

    /**
     * 用于添加标签
     * @param tags 封装好的标签集合
     * @return “某一标签不能重复添加” “不能添加空标签” “添加成功”
     */
    String addTag(List<Tag> tags);

    /**
     * 用于搜索笔记
     * @param Tag 搜索的tag栏
     * @param begin 查询起始点
     * @return list集合，为笔记对象
     */
    List<Note> queryNoteByTag(String Tag,Integer begin);

    /**
     * 用于查询该笔记的所有标签
     * @param noteId 执行查询操作的笔记的id
     * @return tag元素的list集合
     */
    List<Tag> queryTagByNoteId(Integer noteId);

    /**
     * 用于获得拥有该标签的笔记的总个数
     * @param Tag 标签对象
     * @return count：总个数
     */
    Integer queryNoteTotalPageSearchByTag(String Tag);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void uploadFile(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param url 图片插入的地址
     * @param noteId 插入图片的笔记的id
     */
    void insertNotePicture(String url, Integer noteId);

    /**
     *
     * @param notes 需要分类出正在审核的笔记的原笔记
     * @return list集合，分类好后的新笔记集合
     */
    List<Note> checkingNote(List<Note> notes);

    /**
     *
     * @param notes 需要分类出已经驳回了的笔记的原笔记
     * @return list集合，分类好后的新笔记集合
     */
    List<Note> turnBackNote(List<Note> notes);
    /**
     *
     * @param notes 需要分类出已经发布了的笔记的原笔记
     * @return list集合，分类好后的新笔记集合
     */
    List<Note> checkPublishNote(List<Note> notes);

    /**
     *
     * @param noteId 需要执行浏览量加一操作的笔记的id
     */
    void addBrowser(Integer noteId);

    /**
     *
     * @param notes 需要进行排序的note集合
     * @param action 分为“按点赞量排序” “按热度排序” “按点击量排序”
     * @return list集合， 排好序的笔记的集合
     */
    List<Note> sortNote(List<Note> notes,String action);

    /**
     *
     * @param request 对应servlet的req
     * @return request ：返回给对应函数servlet
     */
    HttpServletRequest sort(HttpServletRequest request);

    /**
     *
     * @param zoomName 限定了查询笔记的范围
     * @return list集合，查询后的笔记集合
     */
    List<Note> queryNoteByZoom(String zoomName);

    /**
     * 查找被管理员删除的笔记
     * @param notes 将笔记分类出被删除笔记的原笔记
     * @return 分好类的笔记的集合
     */
    List<Note> checkDeleteNote(List<Note> notes);

    /**
     * 某一笔记执行申述操作
     * @param noteId 需要执行申述操作的笔记的id
     * @return ture为申述成功，false为申述失败
     */

    boolean appeal(Integer noteId);

    /**
     * 对某一笔记执行举报操作
     * @param report 封装好的举报信息
     * @return “举报成功” “举报信息未填写”
     */
    String report(Report report);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void detail(HttpServletRequest request,HttpServletResponse response);
}
