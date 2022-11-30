package com.allwin.bootstrap.aspect;

import com.allwin.bootstrap.aspect.dto.IdempotentRequest;
import com.allwin.bootstrap.dal.dto.IdempotentRequestDto;
import com.allwin.bootstrap.service.IdempotentService;
import com.allwin.bootstrap.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
@Slf4j
@Order(0)
public class IdempotentAspect {

    @Autowired
    IdempotentService idempotentService;

    @Pointcut("@annotation(com.allwin.bootstrap.aspect.annotation.Idempotent)")
    private void processIdempotentRequest() {
    }

    @Around("processIdempotentRequest()")
    public Object processIdempotentRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = logStartTime(joinPoint);

        IdempotentRequestDto response = getRequest(joinPoint);
        if (response != null) {
            logFinishTime(joinPoint, startTime);
            return response.getResponse();
        }

        Object result = joinPoint.proceed();
        log.info("Result : {}", result);
        saveIdempotentRequest(joinPoint, result);

        logFinishTime(joinPoint, startTime);
        return result;
    }

    private static Instant logStartTime(ProceedingJoinPoint joinPoint) {
        Instant startTime = Instant.now();
        log.info("---> Started Idempotent request {} at {}", joinPoint.getSignature().toShortString(), startTime);
        return startTime;
    }

    private static void logFinishTime(ProceedingJoinPoint joinPoint, Instant startTime) {
        Instant finishedTime = Instant.now();
        long diffInMillis = finishedTime.toEpochMilli() - startTime.toEpochMilli();
        log.info("Duration: [{} ms] \t--> Completed Idempotent request{} at {}", diffInMillis,
                joinPoint.getSignature().toShortString(), finishedTime);
    }

    private IdempotentRequestDto getRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("args : {}", args);

        IdempotentRequest idempotentRequest = (IdempotentRequest) joinPoint.getArgs()[0];
        return idempotentService.fetch(idempotentRequest.getIdempotenceKey());
    }

    private void saveIdempotentRequest(ProceedingJoinPoint joinPoint, Object result) throws JsonProcessingException {
        Object[] args = joinPoint.getArgs();
        log.info("args : {}", args);

        IdempotentRequest idempotentRequest = (IdempotentRequest) joinPoint.getArgs()[0];
        IdempotentRequestDto request = new IdempotentRequestDto(idempotentRequest.getIdempotenceKey(),
                JsonUtils.getJsonString(idempotentRequest.toString()), JsonUtils.getJsonString(result.toString()));
        idempotentService.save(request);
    }

/*    @AfterThrowing("within(com.allwin.bootstrap)")
    public void logExceptions(JoinPoint joinPoint) {
        System.out.println("Exception thrown in " + joinPoint.toString());
    }

    @AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
    public void getNameReturningAdvice(String returnString) {
        System.out.println("getNameReturningAdvice executed. Returned String=" + returnString);
    }*/

}
