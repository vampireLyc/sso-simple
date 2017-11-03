package com.sso.controller;

import com.sso.entity.User;
import com.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lyc
 * @date 2017/11/3
 *
 * todo Spring Boot中的Controller的方法中不能直接使用
 * request.getRequestDispatcher(url).forward(request,response); 会报404错误
 */
@Controller
@RequestMapping("/demo2")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        if(userService.checkCookie(request)){
            return "index";
        }
        String url = request.getRequestURL().toString();
        request.setAttribute("url",url);
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user,
                        HttpServletResponse response) throws Exception{
        if(user != null && userService.checkUser(user,response)){
            return "index";
        }
        return "login";
    }
}
