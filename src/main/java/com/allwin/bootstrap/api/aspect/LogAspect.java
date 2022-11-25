package com.allwin.bootstrap.api.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("execution(* com.allwin.bootstrap.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();
        log.info("Started {} at {}", joinPoint.getSignature().getName(), startTime);

        joinPoint.proceed();

        Instant finishedTime = Instant.now();
        long diffInSeconds = finishedTime.getEpochSecond() - startTime.getEpochSecond();
        log.info("Duration: {}\tCompleted {} at {}", diffInSeconds, joinPoint.getSignature().getName(), finishedTime);
    }
}
