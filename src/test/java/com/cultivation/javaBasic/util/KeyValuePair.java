package com.cultivation.javaBasic.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class KeyValuePair<TKey, TValue> {
    private TKey key;
    private TValue value;

    public KeyValuePair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }

    public Type[] getGenericTypes() {
        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
    }
}