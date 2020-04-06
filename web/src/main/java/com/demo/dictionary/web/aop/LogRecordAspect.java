package com.demo.dictionary.web.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dora B
 */
@Aspect
@Configuration
@Slf4j
public class LogRecordAspect {

    /**
     * define the pointcut
     */
    @Pointcut(value = "execution(* com.demo.dictionary.web.controller..*.*(..))")
    public void executeService() {
    }


    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        // get the url and method
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        StringBuilder params = new StringBuilder();
        // get the params
        try {
            if (args.length > 0) {
                if (HttpMethod.POST.name().equals(method) || HttpMethod.PUT.name().equals(method) || HttpMethod.DELETE.name().equals(method)) {
                    for (Object arg : args) {
                        params.append(JSON.toJSONString(arg)).append(",");
                    }
                } else if (HttpMethod.GET.name().equals(method)) {
                    params = new StringBuilder(queryString);
                }
            }
        } catch (Exception ignored) {
        }
        log.info("request start === url: " + url + ", method: " + method + ", params: " + params);
        long startTime = System.currentTimeMillis();
        // get the response
        Object result = pjp.proceed();
        // check processing time, log when time > 200ms
        long time = System.currentTimeMillis() - startTime;
        int max = 200;
        if (time > max) {
            log.debug("request end === url: " + url + ", use time: " + time + "ms");
        }
        return result;
    }

}