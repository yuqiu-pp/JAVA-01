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
import java.util.Random;

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(com.dynamic.source.annotation.ReadOnly)")
    public void dataSourcePointCut() {
    }

    // 方法执行前设置slave，执行后恢复
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // DynamicDataSourceContextHolder.setContextKey(DataSourceType.SLAVE.name());
        // TODO 放在这里做负载均衡是否合适？
        DynamicDataSourceContextHolder.setContextKey(randomeDataSource());
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    private String randomeDataSource() {
        String res = "";
        Random random = new Random();
        int s = random.nextInt(2);
        System.out.println(s);
        switch (s) {
            case 0:
                res = DataSourceType.SLAVE01.name();
                break;
            case 1:
                res = DataSourceType.SLAVE02.name();
                break;
        }

        return res;
    }

    private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method agentMethod = methodSignature.getMethod();
        return joinPoint.getTarget().getClass().getMethod(agentMethod.getName(), agentMethod.getParameterTypes());
    }
}
