package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Aspect
@Component
public class TimeLoggingAspect {

    @Around("execution(* com.nhnacademy.edu.springframework.project.service..*(..))")
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
            long time = stopWatch.getTotalTimeMillis();

            String log = "["+ LocalDateTime.now() + "] [" + className +"].[" + methodName +"] [" + time +"ms]";

            logging(log);

//            System.out.println("["+className+"].["+methodName+"] ["+ stopWatch.getTotalTimeMillis()+"ms]");
        }
    }

    private void logging(String log) {
        try {
            String path = "src/main/resources/log/elapse.log";

            File file = new File(path);

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(log);
            bw.newLine();

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("log 찍기 실패");
        }
    }
}
