package ru.clevertec.setter;

import java.lang.reflect.Field;

public interface FieldValueSetter<T> {

    void setFieldValue(T instance, Field field, String value) throws Exception;
    boolean supports(Class<?> fieldType);
}
