package ru.clevertec.deserializer;

import ru.clevertec.annotation.JsonField;

import java.lang.reflect.Field;
import java.util.*;

public class JsonDeserializer {

    public <T> T deserialize(String jsonString, Class<T> clazz) throws Exception {
        jsonString = jsonString.trim();

        if (jsonString.charAt(0) != '{' ||  jsonString.charAt(jsonString.length() - 1) != '}') {
            throw new IllegalArgumentException("Invalid json string");
        }

        Map<String, String> jsonMap = parseJsonToMap(jsonString);

        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            setFieldValue(instance, entry.getKey(), entry.getValue(), clazz);
        }

        return instance;
    }

    private Map<String, String> parseJsonToMap(String jsonString) {
        Map<String, String> jsonMap = new HashMap<>();

        jsonString = jsonString.trim();
        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();

        String[] keyValuePairs = jsonString.split(",(?![^{}\\[\\]]*\\}|[^{}\\[\\]]*\\])");

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":", 2);

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim();

            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            jsonMap.put(key, value);
        }
        return jsonMap;
    }

    private Field getField(Class<?> clazz, String key) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(key);
        } catch (NoSuchFieldException e) {
            for (Field f : clazz.getDeclaredFields()) {
                JsonField jsonField = f.getAnnotation(JsonField.class);
                if (jsonField != null && jsonField.value().equals(key)) {
                    field = f;
                }
            }
        }
        return field;
    }

    private <T> void setFieldValue(T instance, String fieldName, String value, Class<?> clazz) throws Exception {
        Field field = getField(clazz, fieldName);
        field.setAccessible(true);

        Class<?> fieldType = field.getType();

        if (fieldType == String.class) {
            field.set(instance, value);
        } else if (fieldType == int.class) {
            field.set(instance, Integer.parseInt(value));
        } else if (fieldType == boolean.class) {
            field.set(instance, Boolean.parseBoolean(value));
        } else if (List.class.isAssignableFrom(fieldType)) {
            field.set(instance, parseList(value));
        } else {
            field.set(instance, deserialize(value, fieldType));
        }
    }

    private List<?> parseList(String jsonList) {
        List<Object> list = new ArrayList<>();

        jsonList = jsonList.trim();

        if (jsonList.startsWith("[") && jsonList.endsWith("]")) {
            jsonList = jsonList.substring(1, jsonList.length() - 1).trim();
        }

        String[] keyValue = jsonList.split(":");
        String value = keyValue[1].trim().replace("\"", "").replace("}", "");
        list.add(value);

        return list;
    }
}
