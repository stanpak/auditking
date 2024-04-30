package com.tribium.auditking.core.utilities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapConverter {

    public static Map<String, Object> convertObjectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value != null) {
                if (value.getClass().isPrimitive() || value instanceof String || value instanceof Number || value instanceof Boolean) {
                    map.put(field.getName(), value);
                } else if (value instanceof Map) {
                    map.put(field.getName(), value);
                } else {
                    map.put(field.getName(), convertObjectToMap(value));
                }
            }
        }
        return map;
    }
}
