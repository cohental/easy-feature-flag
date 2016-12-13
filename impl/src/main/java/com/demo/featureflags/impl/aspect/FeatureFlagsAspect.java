package com.demo.featureflags.impl.aspect;

import com.demo.featureflags.api.annotations.FeatureFlag;
import com.demo.featureflags.api.annotations.ReturnParameter;
import com.demo.featureflags.api.services.FeatureFlagsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class FeatureFlagsAspect {

    @Autowired
    private FeatureFlagsService featureFlagsService;

    @Around(value = "@annotation(featureFlag)", argNames = "joinPoint, featureFlag")
    public Object around(ProceedingJoinPoint joinPoint, FeatureFlag featureFlag) throws Throwable {

        if (featureFlagsService.isActive(featureFlag.feature())) {
            return joinPoint.proceed();
        }

        switch (featureFlag.whenInactive()) {
            case Null:
                return null;
            case True:
                return true;
            case False:
                return false;
            case Zero:
            case NewInstance:
            case EmptyArray:
            case EmptyCollection:
                return getZeroOrEmptyCollection(joinPoint);
            case MethodParameter:
                return getMethodParamValue(joinPoint);
            default:
                return null;
        }
    }

    private Object getMethodParamValue(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();

        Annotation[][] parameterAnnotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();

        Object[] methodArguments = joinPoint.getArgs();
        int argumentIdx = 0;

        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ReturnParameter) {
                    return methodArguments[argumentIdx];
                }
            }
            argumentIdx++;
        }
        return null;
    }

    private Object getZeroOrEmptyCollection(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String nilStringValue = "0";
        Object retValue;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Class returnClassType = signature.getReturnType();
        Class supperClass = returnClassType.getSuperclass();

        if (supperClass != null && supperClass.equals(Number.class)) {
            Constructor constructor = returnClassType.getConstructor(String.class);
            retValue = constructor.newInstance(nilStringValue);
        } else if (returnClassType.isInterface()) {
            retValue = ReflectionUtils.getInterfaceInstance(returnClassType);
        } else if (returnClassType.isArray()) {
            retValue = Array.newInstance(returnClassType.getComponentType(), 0);
        } else {
            // try to initiate empty constructor if exists
            retValue = returnClassType.newInstance();
        }
        return retValue;
    }
}
