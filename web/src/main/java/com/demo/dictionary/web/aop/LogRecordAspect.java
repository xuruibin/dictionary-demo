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
 * 定义一个切面
 */
@Aspect
@Configuration
@Slf4j
public class LogRecordAspect {

    /**
     * 定义切点Pointcut
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

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        StringBuilder params = new StringBuilder();
        //获取请求参数集合并进行遍历拼接
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
        log.info("请求开始===地址:" + url + ", 类型:" + method + ", 参数:" + params);
        long startTime = System.currentTimeMillis();
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        int max = 200;
        if (time > max) {
            log.debug("请求结束===地址:" + url + "，耗时:" + time + "毫秒");
        }
        return result;
    }

}