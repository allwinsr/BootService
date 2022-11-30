package com.allwin.bootstrap.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {
/*
    @Around("execution(* com.allwin.bootstrap.service.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();
        log.info("Started {} at {}", joinPoint.getSignature().toShortString(), startTime);

        Object result = joinPoint.proceed();

        Instant finishedTime = Instant.now();
        long diffInMillis = finishedTime.toEpochMilli() - startTime.toEpochMilli();
        log.info("Duration: [{} ms] \tCompleted {} at {}", diffInMillis, joinPoint.getSignature().toShortString(),
                finishedTime);
    }*/


/*
    @Pointcut("@annotation(com.allwin.bootstrap.api.aspect.Idempotent)")
    public void loggableMethods(ProceedingJoinPoint joinPoint) {
        Instant startTime = Instant.now();
        log.info("----> Started the idempotent call {} at {}", joinPoint.getSignature().toShortString(), startTime);
    }*/
}
