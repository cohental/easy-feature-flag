package com.demo.featureflags.api.enums;


public enum ReturnValue {
    Null,
    True,
    False,
    Zero,
    EmptyCollection,
    EmptyArray,
    NewInstance,
    /**
     * see @ReturnParameter annotation on the method parameter
     */
    MethodParameter;
    public String getName(){
        return this.name();
    }
}
