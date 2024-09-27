package ru.clevertec.setter;

import java.lang.reflect.Field;

public class StringFieldValueSetter implements FieldValueSetter<Object> {

    @Override
    public void setFieldValue(Object instance, Field field, String value) throws Exception {
        field.set(instance, value);
    }

    @Override
    public boolean supports(Class<?> fieldType) {
        return fieldType == String.class;
    }
}
