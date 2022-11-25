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

    @Around("execution(* com.allwin.bootstrap.service.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();
        log.info("Started {} at {}", joinPoint.getSignature().toShortString(), startTime);

        joinPoint.proceed();

        Instant finishedTime = Instant.now();
        long diffInMillis = finishedTime.toEpochMilli() - startTime.toEpochMilli();
        log.info("Duration: [{} ms] \tCompleted {} at {}", diffInMillis, joinPoint.getSignature().toShortString(),
                finishedTime);
    }
}
