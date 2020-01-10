package org.study.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author lipo
 * @version v1.0
 * @date 2020-01-10 14:37
 */
@Aspect
@Component
@Slf4j
public class GlobalExceptionLogAop {

    @Pointcut("execution(* org..controller..*.*(..))")
    public void point() {

    }

    @AfterThrowing(pointcut = "point()", throwing = "e")
    public void afterThrowing(Throwable e) {
        log.info("afterThrowing");
        if (e instanceof BizException) {
            log.warn(e.getMessage());
        } else {
            log.error(e.getMessage(), e);
        }
    }

    @Before(value = "point()")
    public void before() {
        log.info("before");
    }

    @After(value = "point()")
    public void after() {
        log.info("after");
    }

    @Around(value = "point()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around-begin");
        Object o = pjp.proceed();

        log.info("around-end");
        return o;
    }

    @AfterReturning(value = "point()")
    public void afterReturning() {
        log.info("afterReturning");
    }


}
