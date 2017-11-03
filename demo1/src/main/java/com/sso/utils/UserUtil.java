package com.sso.utils;

import com.sso.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lyc on 2017/11/3.
 */
public class UserUtil {

    public static boolean checkCookie(HttpServletRequest request, StringRedisTemplate stringRedisTemplate){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                //遍历cookie，如果存在cookie作为key，可以在redis中存在相应的value，则返回index
                String value = cookie.getValue();
                if(stringRedisTemplate.hasKey(value)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkUser(User user, HttpServletResponse response) {

        return false;
    }
}
