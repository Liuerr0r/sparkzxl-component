package com.sparksys.database.aspect;

import com.sparksys.database.annonation.InjectionResult;
import com.sparksys.database.core.InjectionCore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * description: InjectionResult 注解的 AOP 工具
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 08:49:08
*/
@Aspect
@AllArgsConstructor
@Slf4j
public class InjectionResultAspect {
    private final InjectionCore injectionCore;


    @Pointcut("@annotation(com.sparksys.database.annonation.InjectionResult)")
    public void methodPointcut() {
    }


    @Around("methodPointcut()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint pjp, InjectionResult anno) throws Throwable {
        try {
            return injectionCore.injection(pjp, anno);
        } catch (Exception e) {
            log.error("AOP拦截@RemoteResult出错", e);
            return pjp.proceed();
        }
    }
}
