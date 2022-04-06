package com.cjj.www.service;

public interface UserService {
    boolean userLogin(String username,String password);
    boolean userRegister(String username,String password);
}
