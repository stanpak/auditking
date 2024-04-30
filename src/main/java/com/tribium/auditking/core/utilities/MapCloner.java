package com.tribium.auditking.core.utilities;

import java.util.HashMap;
import java.util.Map;

public class MapCloner {
    public static Map<String, Object> cloneMap(Map<String, Object> originalMap) {
        Map<String, Object> clonedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                // Recursively clone nested maps
                clonedMap.put(key, cloneMap((Map<String, Object>) value));
            } else {
                // Copy non-nested values directly
                clonedMap.put(key, value);
            }
        }
        return clonedMap;
    }
}
