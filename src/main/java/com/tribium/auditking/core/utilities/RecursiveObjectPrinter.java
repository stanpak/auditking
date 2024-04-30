package com.tribium.auditking.core.utilities;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class RecursiveObjectPrinter {

    private  Set<Object> visited = new HashSet<>();

    public static void main(String[] args) {
        // Example object with potential cycles
        Node node1 = new Node("Node 1");
        Node node2 = new Node(10L);
        node1.setNext(node2);
        node2.setNext(node1);

        // Print the contents of the object
        new RecursiveObjectPrinter().printObjectContents(node1);
    }

    public  void printObjectContents(Object obj) {
        visited.clear();
        printObjectContentsRecursive(obj, 0);
    }

    private  void printObjectContentsRecursive(Object obj, int depth) {
        if (obj == null || visited.contains(obj)) {
            return;
        }

        visited.add(obj);

        Class<?> clazz = obj.getClass();
        if (clazz.isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object arrayElement = Array.get(obj, i);
                printObjectContentsRecursive(arrayElement, depth);
            }
        } else if (obj instanceof Collection) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object element : collection) {
                printObjectContentsRecursive(element, depth);
            }
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(indent(depth) + entry.getKey() + " -> ");
                printObjectContentsRecursive(entry.getValue(), depth + 1);
            }
        } else {
            // For non-array, non-collection objects
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(obj);
                    System.out.println(indent(depth) + field.getName() + " = " + fieldValue);
                    if(!isPrimitive(fieldValue))
                        printObjectContentsRecursive(fieldValue, depth + 1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isPrimitive(Object o) {
        return (o instanceof String) || (o instanceof Long)|| (o instanceof Integer)|| (o instanceof Short)||
                (o instanceof Double)|| (o instanceof Float)|| (o instanceof Date);
    }

    private  String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    static class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
