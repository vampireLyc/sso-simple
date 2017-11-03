package com.sso.service;

import com.sso.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lyc on 2017/11/3.
 */
public interface UserService {
    boolean checkCookie(HttpServletRequest request);

    boolean checkUser(User user, HttpServletResponse response);
}
