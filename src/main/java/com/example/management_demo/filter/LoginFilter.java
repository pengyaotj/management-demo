package com.example.management_demo.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.management_demo.pojo.Result;
import com.example.management_demo.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
// @WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);
        // 2.判断是否包括login
        if(url.contains("login")) {
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
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
            return;
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
            return;
        }
        // 6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
