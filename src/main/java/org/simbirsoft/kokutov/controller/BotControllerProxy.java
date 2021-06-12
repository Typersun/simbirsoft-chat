package org.simbirsoft.kokutov.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class BotControllerProxy {

    @Pointcut("within(org.simbirsoft.kokutov.controller.BotController)")
    public void executeConcurrency() {
    }

    @Before("executeConcurrency()")
    public void beforeLog(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName());
    }
    @AfterReturning("executeConcurrency()")
    public void afterLog(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature().getName());
    }

}
