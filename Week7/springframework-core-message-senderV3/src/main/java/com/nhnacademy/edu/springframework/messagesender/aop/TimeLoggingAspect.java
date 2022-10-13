package com.nhnacademy.edu.springframework.messagesender.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLoggingAspect {

//    @Pointcut("execution(* com.nhnacademy.edu.springframework.messagesender.service.*.*(..))")
//    public void timeLogging(){}

    @Around("execution(* com.nhnacademy.edu.springframework.messagesender.service.*.sendMessage(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch("logger");

        try {
            stopWatch.start();

            Object retVal = pjp.proceed();
            return retVal;
        } finally {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
