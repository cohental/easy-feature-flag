package com.demo.featureflags.api.annotations;

import com.demo.featureflags.api.enums.ReturnValue;
import com.demo.featureflags.api.enums.Feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FeatureFlag {

    Feature feature();

    ReturnValue whenInactive() default ReturnValue.Null;

}
