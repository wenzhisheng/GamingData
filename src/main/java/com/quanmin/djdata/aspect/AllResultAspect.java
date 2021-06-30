package com.quanmin.djdata.aspect;

import com.quanmin.djdata.exception.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: ate
 * @Description: 同意返回切面
 * @CreateDate: 2019-11-11 16:53
 * @ClassName: com.quanmin.djdata.aspect.AllResultAspect
 */
@Aspect
@Component
public class AllResultAspect {

    /**
     * @author: ate
     * @description: controller拦截
     * @date: 2019/11/11 16:55
     * @param: [proceedingJoinPoint]
     * @return: java.lang.Object
     */
    @Around("execution(* com.*..controller.*.*(..))&&(!@annotation(com.quanmin.djdata.annotation.OtherReturn))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取目标方法
        Method proxyMethod = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Method targetMethod = proceedingJoinPoint.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        boolean isPublic = Modifier.isPublic(targetMethod.getModifiers());
        if(!isPublic){
            return proceedingJoinPoint.proceed();
        }
        // 获取返回信息
        Object result = proceedingJoinPoint.proceed();
        //如果返回值 带有ERROR:前缀 则认为是错误信息
        if(result instanceof String && ((String) result).startsWith("ERROR:")){
            String[] resultStr = ((String) result).split(":");
            return ResultUtil.error(-1,resultStr[1]);
        }else{
            return ResultUtil.success(result);
        }
    }

}
