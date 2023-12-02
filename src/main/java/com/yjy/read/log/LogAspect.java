package com.yjy.read.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 在service包中的所有类的所有方法 在执行前 都会调用此方法
    @Before("execution(* com.yjy.read.service..*.*(..))")
    public void log() {
        logger.info("before method run");
    }

    @After("execution(* com.yjy.read.service..*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("after method run," + joinPoint.getTarget().getClass() + ",args="
                + Arrays.asList(joinPoint.getArgs()) + ",method=" + joinPoint.getSignature());
    }
}
