package ru.clevertec.setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListFieldValueSetter implements FieldValueSetter<Object> {

    @Override
    public void setFieldValue(Object instance, Field field, String value) throws Exception {
        field.set(instance, parseList(value));
    }

    @Override
    public boolean supports(Class<?> fieldType) {
        return List.class.isAssignableFrom(fieldType);
    }

    private List<?> parseList(String jsonList) {
        List<Object> list = new ArrayList<>();
        jsonList = jsonList.trim();

        if (jsonList.startsWith("[") && jsonList.endsWith("]")) {
            jsonList = jsonList.substring(1, jsonList.length() - 1).trim();
        }

        String[] values = jsonList.split(",");
        for (String value : values) {
            value = value.trim();
            if (value.startsWith("{") && value.endsWith("}")) {
                value = value.substring(1, value.length() - 1).trim();
            }
            String[] keyValue = value.split(":");
            if (keyValue.length == 2) {
                String actualValue = keyValue[1].trim().replace("\"", "").trim();
                list.add(actualValue);
            }
        }
        return list;
    }
}
