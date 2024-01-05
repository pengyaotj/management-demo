package com.example.management_demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect {
    @Pointcut("@annotation(com.example.management_demo.aop.MyLog)")
    private void pt() {}

    @Before("pt()")
    public void before() {
        log.info("MyAspect ... before ...");_
    }
}
