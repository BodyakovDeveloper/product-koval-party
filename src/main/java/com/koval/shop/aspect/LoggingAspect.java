package com.koval.shop.aspect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Around("@annotation(com.koval.shop.annotation.Logging)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        Object result;
        try {
            Session session = entityManager.unwrap(Session.class);
            session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

            result = joinPoint.proceed();

            Statistics statistics = session.getSessionFactory().getStatistics();
            log.debug("SQL Queries: {}, Time: {} ms",
                    statistics.getQueryExecutionCount(),
                    statistics.getQueryExecutionMaxTime());
        } catch (Exception ex) {
            stopWatch.stop();
            log.info("monitor-method: {}, Time: {}, exception: {}", methodName,
                    stopWatch.getTotalTimeMillis(), ex.getMessage());
            throw ex;
        }
        stopWatch.stop();
        log.info("monitor-method: {}, Time: {}", methodName, stopWatch.getTotalTimeMillis());

        return result;
    }
}