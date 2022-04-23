package com.cjj.www.pojo;

public class Notice {
    private Integer id;
    private String title;
    private String main;

    public Notice(Integer id, String title, String main) {
        this.id = id;
        this.title = title;
        this.main = main;
    }

    public Notice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
