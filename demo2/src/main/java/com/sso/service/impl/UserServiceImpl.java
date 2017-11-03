package com.sso.service.impl;

import com.sso.entity.User;
import com.sso.repository.UserRepository;
import com.sso.service.UserService;
import com.sso.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lyc on 2017/11/3.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkCookie(HttpServletRequest request) {
        return UserUtil.checkCookie(request,stringRedisTemplate);
    }

    @Override
    public boolean checkUser(User user, HttpServletResponse response) {
        User u = userRepository.findUserByNameAndPassword(user.getName(), user.getPassword());
        if(u != null){
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("cookie",uuid);
            cookie.setDomain("lyc.com");
            response.addCookie(cookie);
            //cookie保存到Redis，并设置有效时间
            stringRedisTemplate.opsForValue().set(uuid, user.getName(),60, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间10秒
            return true;
        }
        return false;
    }
}
