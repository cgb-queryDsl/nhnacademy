package com.nhnacademy.edu.springframework.project.v2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLoggerAspect {

    @Around("execution(* com.nhnacademy.edu.springframework.project.v2.service..*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            Object retVal = pjp.proceed();

            return retVal;
        } finally {
            stopWatch.stop();
            MethodSignature signature = (MethodSignature) pjp.getSignature();

            String className = signature.getMethod().getDeclaringClass().getSimpleName();
            String methodName = signature.getMethod().getName();

            System.out.println("["+className+"].["+methodName+"] ["+ stopWatch.getTotalTimeMillis()+"ms]");
        }
    }
}
