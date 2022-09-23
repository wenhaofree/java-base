package club.fuwenhao.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面日志打印
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2020/5/20 1:42 下午
 */
@Aspect
@Component
public class MyLogAspect {
    @Pointcut("@annotation(club.fuwenhao.config.MyLog)")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    private void logAround(ProceedingJoinPoint proceedingJoinPoint) {
        final Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg).append(",");
        }
        final String name = proceedingJoinPoint.getSignature().getName();
        System.out.println(String.format("执行方法名为:%s,参数为：%s", name, stringBuilder.toString()));
        try {
            //继续执行
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(name + "：方法执行结束");
    }
}
