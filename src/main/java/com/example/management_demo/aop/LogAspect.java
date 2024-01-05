package com.example.management_demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(com.example.management_demo.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {



        Object result = proceedingJoinPoint.proceed();

        //记录操作日志


        return result;
    }
}
