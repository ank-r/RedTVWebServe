package com.redtv.redtvwebserve.interceptor;

import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName LogInterceptor
 * @Description
 * @Author admin
 * @Time 2023/2/20 16:51
 * @Version 1.0
 */

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final RedisTemplate redisTemplate ;

    public LogInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (url.startsWith("/get/file/") || url.startsWith("/danmaku/v3/")
        || url.startsWith("/login/ordinary")||url.startsWith("/login/admin")||url.startsWith("/user/register")||url.startsWith("/upload/")
                ||url.startsWith("/captcha/get")
        ){
            //文件请求 , 或者弹幕请求 不用token验证
            return true;
        }
        //登录验证
        String  token = request.getHeader("Authorization");
        if ( token == null || token =="" || token.length() == 0 ){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            System.out.println("访问文件： " + url);
            writer.write("403");
            return false;
        }
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(token);
        HostHolder.setUser(userInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("\n-------- LogInterception.postHandle --- ");
        // System.out.println("Request URL: " + request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HostHolder.clear();

    }
}
