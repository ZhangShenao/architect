package william.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/5 16:45
 * @Description:日志切面
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(* william.spring.aop.Calculator.* (..))")
    private void pointCut(){}

    @Before("pointCut()")
    public void beforeLog(JoinPoint joinPoint){
        logMethodSignature("Before Log: ",joinPoint);
    }

    @AfterReturning(pointcut = "pointCut()",returning = "retVal")
    public void afterLog(JoinPoint joinPoint,Object retVal){
        logMethodSignature("After Log: ",joinPoint);
        System.err.println("Result: " + retVal);
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "throwable")
    public void afterThrowingLog(JoinPoint joinPoint,Throwable throwable){
        logMethodSignature("After Throwing Log: ",joinPoint);
        System.err.println("Error: " + throwable.getMessage());
    }

    @Around("pointCut()")
    public void aroundLog(ProceedingJoinPoint joinPoint){
        try {
            logMethodSignature("Around Before Log: ",joinPoint);
            Object retVal = joinPoint.proceed();
            logMethodSignature("Around After Log: ",joinPoint);
        }catch (Throwable e){
            logMethodSignature("Around Throwing Log: ",joinPoint);
        }

    }

    private void logMethodSignature(String msg,JoinPoint joinPoint){
        System.err.println(msg + joinPoint.getSignature() + ",args: " + Arrays.toString(joinPoint.getArgs()));
    }
}
