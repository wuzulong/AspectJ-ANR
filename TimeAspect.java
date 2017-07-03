package com.example.aspectjdemo;  
  
import android.app.Activity;  
import android.content.Context;  
import android.util.Log;  
  
import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Pointcut;  
import org.aspectj.lang.reflect.MethodSignature; 
  
/** 
 * Created by wuzulong on 2017/6/30.
 */
@Aspect
public class TimeAspect {

  //筛选出所有通过注解的方法和构造函数
  private static final String POINTCUT_METHOD =
      "execution(@org.android10.gintonic.annotation.DebugTrace * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@org.android10.gintonic.annotation.DebugTrace *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithDebugTrace() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedDebugTrace() {}

  //代码注入在使用"@DebugTrace"注解的地方生效
  @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    final WatchTime WatchTime = new WatchTime();
    WatchTime.start();
    Object result = joinPoint.proceed();
    WatchTime.stop();

    DebugLog.log(className, buildLogMessage(methodName, WatchTime.getTotalTimeMillis()));

    return result;
  }

  /**
   * 构造日志信息，用 Android Log 输出
   */
  private static String buildLogMessage(String methodName, long methodDuration) {
    StringBuilder message = new StringBuilder();
    message.append("Gintonic --> ");
    message.append(methodName);
    message.append(" --> ");
    message.append("[");
    message.append(methodDuration);
    message.append("ms");
    message.append("]");
    return message.toString();
  }
}