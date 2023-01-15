package by.it_academy.calorie_diary.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Aspect
@Component
public class ServiceLogAspect extends BaseAspect{
    @Pointcut("execution(* by.it_academy.calorie_diary.services..*(..)) " +
            "&& !@annotation(by.it_academy.calorie_diary.logging.ExcludeLog))")
    public void isServiceLayer() {
    }

    @Before("isServiceLayer()")
    public void logControllersBefore(JoinPoint joinPoint) {
        log.info(BEFORE_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getArgsWithName(joinPoint));
    }

    @AfterReturning(pointcut = "isServiceLayer()", returning = "result")
    public void logControllerAfter(JoinPoint joinPoint, Object result) {
        log.info(AFTER_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getStringInstanceOf(Optional.ofNullable(result).orElse("")),
                getArgsWithName(joinPoint));
    }

    @AfterThrowing(pointcut = "isServiceLayer()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error(AFTER_THROWING_PATTERN,
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(),
                e.getCause() != null? e.getCause() : "NULL",
                e.getMessage());
    }
}
