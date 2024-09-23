package ru.practicum.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * TODO pointCut for aspect
 */
@Aspect
@Component
@Slf4j
public class EwmLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(EwmLogger)")
    public void logging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.info("Request received: {}(): {}", methodName, Arrays.toString(args));
        Object result = joinPoint.proceed();
        if (result != null) {
            logger.info("Request {}() - {}", methodName, result);
        }
    }
}
