package com.tribium.auditking.core.utilities;

import java.util.HashMap;
import java.util.Map;

public class MapComparator {

    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map1.put("key3", "value3");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key1", "value1");
        map2.put("key2", "value2_updated");
        map2.put("key4", "value4");

        Map<String, MapDifference> differences = compareMaps(map1, map2);

        System.out.println("Differences:");
        for (Map.Entry<String, MapDifference> entry : differences.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, MapDifference> compareMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, MapDifference> differences = new HashMap<>();
        compareMapsRecursive(map1, map2, differences);
        return differences;
    }

    private static void compareMapsRecursive(Map<String, Object> map1, Map<String, Object> map2, Map<String, MapDifference> differences) {
        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = map2.get(key);

            if (value2 == null) {
                differences.put(key, new MapDifference(value1, null, Operation.Delete));
            } else if (!value2.equals(value1)) {
                if (value2 instanceof Map && value1 instanceof Map) {
                    compareMapsRecursive((Map<String, Object>) value1, (Map<String, Object>) value2, differences);
                } else {
                    differences.put(key, new MapDifference(value1, value2, Operation.Update));
                }
            }
        }

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            String key = entry.getKey();
            Object value2 = entry.getValue();
            Object value1 = map1.get(key);

            if (value1 == null) {
                differences.put(key, new MapDifference(null, value2, Operation.Add));
            }
        }
    }

    public static class MapDifference {
        private final Object oldValue;
        private final Object newValue;
        private final Operation operation;

        public MapDifference(Object oldValue, Object newValue, Operation operation) {
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.operation = operation;
        }

        @Override
        public String toString() {
            return "Operation: " + operation +
                    ", Old Value: " + oldValue +
                    ", New Value: " + newValue;
        }
    }

    private enum Operation {
        Add, Update, Delete
    }
}