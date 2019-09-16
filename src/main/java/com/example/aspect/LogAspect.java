package com.example.aspect;

import com.example.entity.Log;
import com.example.services.ILog;
import com.example.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @date 2019/9/10 14:12
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ILog logServices;

    @Pointcut(value = "@annotation(com.example.annotation.Log)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime-beginTime;
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point,long time){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        com.example.annotation.Log logAnnotation = method.getAnnotation(com.example.annotation.Log.class);
        if(logAnnotation != null){
            log.setOperation(logAnnotation.value());
        }
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className+"."+methodName);

        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            log.setParams(params);
        }
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        log.setIp(IPUtils.getIpAddr(request));
        log.setUsername("admin");
        log.setTime((int)time);
        log.setCreatetime(LocalDateTime.now());
        logServices.saveSysLog(log);
    }
}
