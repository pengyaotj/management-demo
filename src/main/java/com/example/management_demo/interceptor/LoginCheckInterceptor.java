package com.example.management_demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.management_demo.pojo.Result;
import com.example.management_demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        // 1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);
        // 2.判断是否包括login
        if(url.contains("login")) {
            log.info("登录操作，放行");
            return true;
        }
        // 3.获取请求头中的令牌（token）
        String jwt = req.getHeader("token");
        // 4.判断令牌是否存在
        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，返回未登录的信息");
            Result err =  Result.error("NOT_LOGIN");
            // 手动转换
            String notLogin =  JSONObject.toJSONString(err);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 5.校验JWT令牌
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();

            log.info("解析令牌失败，返回未登录的信息");

            Result err =  Result.error("NOT_LOGIN");
            // 手动转换
            String notLogin =  JSONObject.toJSONString(err);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 6.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
