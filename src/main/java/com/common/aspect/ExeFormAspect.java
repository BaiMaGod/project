package com.common.aspect;


import com.common.annotation.ExeForm;
import com.common.form.AfterForm;
import com.common.form.BeforeForm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 入参 前置/后置 处理，切面处理类
 *
 * @author yc
 */
@Slf4j
@Aspect
@Component
public class ExeFormAspect {

    @Pointcut("@annotation(com.common.annotation.ExeForm)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    private void before(JoinPoint point){
        try {

            log.info("执行-入参前置方法");

            exeForm(point, BeforeForm.class);
        }catch (Exception e){
            log.error("执行-入参前置方法异常!已被捕捉，不影响程序继续运行。",e);
        }
    }

    @After("logPointCut()")
    private void after(JoinPoint point){
        try {

            log.info("执行-入参后置方法");

            exeForm(point, AfterForm.class);
        }catch (Exception e){
            log.error("执行-入参后置方法异常!已被捕捉，不影响程序继续运行。",e);
        }
    }


    /**
     * 执行form处理规则
     * @param joinPoint 切点
     * @param interfaceClass 预先定义处理方法的接口类对象
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void exeForm(JoinPoint joinPoint,Class interfaceClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //判断该方法是否被 @ExeForm 注解
        boolean annotationMethod = method.isAnnotationPresent(ExeForm.class);

        //获取方法注解的值，即指定要执行的方法名集合
        String[] methodValue = method.getAnnotation(ExeForm.class).value();

        //将 指定要执行的方法名 装入 set
        Set<String> exeFormValue = new HashSet<>(Arrays.asList(methodValue));

        //得到被切方法的参数
        Object[] args = joinPoint.getArgs();
        //得到被切方法的参数信息
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < args.length; i++) {
            //判断该参数是否被 @ExeForm 注解
            boolean annotationArg = parameters[i].isAnnotationPresent(ExeForm.class);

            //如果方法 或 参数 被注解，则需要对该参数对象进行处理
            if(annotationMethod || annotationArg){
                if (annotationArg){
                    //获取参数注解的值，即指定要执行的方法名集合
                    String[] argValue = parameters[i].getAnnotation(ExeForm.class).value();
                    //将 指定要执行的方法名 装入 set
                    exeFormValue.addAll(Arrays.asList(argValue));
                }

                //执行方法
                exeMethods(args[i],interfaceClass,exeFormValue);
            }

        }

    }


    /**
     * 执行方法
     * @param object 要执行方法的对象
     * @param interfaceClass 预先定义处理方法的接口类对象
     * @param exeFormValue 指定要执行的方法名集合，如果为空，则执行接口的全部方法
     */
    private void  exeMethods(Object object,Class interfaceClass,Set<String> exeFormValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> objectClass = object.getClass();

        //如果 要执行方法的对象 实现了 interfaceClass 接口
        if( interfaceClass.isAssignableFrom(objectClass) ){
            Method[] interfaceMethods = interfaceClass.getMethods();

            //循环执行 对象 中的 接口方法
            for (Method interfaceMethod : interfaceMethods) {
                //先获取对象里的方法
                Method objectMethod = objectClass.getMethod(interfaceMethod.getName());

                //如果指定的方法名集合为空，或者该方法包含在指定的方法名里，则需要执行
                if(exeFormValue.isEmpty() || exeFormValue.contains(objectMethod.getName())){
                    //执行对象的方法
                    objectMethod.invoke(object);
                }
            }
        }
    }


}
