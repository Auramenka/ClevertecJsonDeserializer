package ru.clevertec.setter;

import java.lang.reflect.Field;

public class BooleanFieldValueSetter implements FieldValueSetter<Object> {

    @Override
    public void setFieldValue(Object instance, Field field, String value) throws Exception {
        field.set(instance, Boolean.parseBoolean(value));
    }

    @Override
    public boolean supports(Class<?> fieldType) {
        return fieldType == boolean.class;
    }
}
