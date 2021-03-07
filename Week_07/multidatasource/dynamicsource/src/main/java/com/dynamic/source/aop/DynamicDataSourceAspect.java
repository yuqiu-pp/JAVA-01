package com.dynamic.source.aop;

import com.dynamic.source.config.DataSourceType;
import com.dynamic.source.context.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(com.dynamic.source.annotation.ReadOnly)")
    public void dataSourcePointCut() {
    }

    // 方法执行前设置slave，执行后恢复
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSourceContextHolder.setContextKey(DataSourceType.SLAVE.name());
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method agentMethod = methodSignature.getMethod();
        return joinPoint.getTarget().getClass().getMethod(agentMethod.getName(), agentMethod.getParameterTypes());
    }
}
