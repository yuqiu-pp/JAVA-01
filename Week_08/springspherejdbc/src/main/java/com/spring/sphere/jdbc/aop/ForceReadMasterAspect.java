package com.spring.sphere.jdbc.aop;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(-1) // 保证该AOP在@Transactional之前执行
public class ForceReadMasterAspect {

    @Pointcut("@annotation(com.spring.sphere.jdbc.annotation.ForceReadMaster)")
    public void forceReadMastPointCut() {
    }

    // 方法执行前设置slave，执行后恢复
    @Around("forceReadMastPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.setMasterRouteOnly();
            return joinPoint.proceed();
        }
    }

    private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method agentMethod = methodSignature.getMethod();
        return joinPoint.getTarget().getClass().getMethod(agentMethod.getName(), agentMethod.getParameterTypes());
    }

}
