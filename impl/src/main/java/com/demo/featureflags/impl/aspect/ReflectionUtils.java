package com.demo.featureflags.impl.aspect;

import java.util.*;

class ReflectionUtils {

    private static final Map<Class<?>, Class<?>> interfaceImplementations = new HashMap<>();

    static {
        interfaceImplementations.put(Collection.class, ArrayList.class);
        interfaceImplementations.put(List.class, ArrayList.class);
        interfaceImplementations.put(Set.class, HashSet.class);
        interfaceImplementations.put(SortedSet.class, TreeSet.class);
        interfaceImplementations.put(Queue.class, LinkedList.class);
        interfaceImplementations.put(Map.class, HashMap.class);
        interfaceImplementations.put(SortedMap.class, TreeMap.class);
    }

    static <T> T getInterfaceInstance(Class<T> interfaceType) throws InstantiationException, IllegalAccessException {
        Class impl = interfaceImplementations.get(interfaceType);
        if (impl == null) {
            throw new InstantiationException("Cannot instantiate interface [ " + interfaceType.getName() + "]");
        }
        return (T) impl.newInstance();
    }
}
