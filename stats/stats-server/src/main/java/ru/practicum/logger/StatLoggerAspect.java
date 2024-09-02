package ru.practicum.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class StatLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(StatLogger)")
    public void logging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info(">> {}() - {}", methodName, Arrays.toString(args));
        Object result = joinPoint.proceed();
        if (result != null) {
            logger.info("<< {}() - {}", methodName, result);
        }
        //return result;
    }
}
