package by.smirnov.newsproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Pointcut("within(by.smirnov.newsproject.service..*))")
    public void aroundServicePointcut() {
        //Declares pointcut
    }

    @Around("aroundServicePointcut()")
    public Object logAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Method {} in {} started", joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringType());
        Object proceed = joinPoint.proceed();
        log.debug("Method {} in {} finished", joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringType());
        return proceed;
    }
}
