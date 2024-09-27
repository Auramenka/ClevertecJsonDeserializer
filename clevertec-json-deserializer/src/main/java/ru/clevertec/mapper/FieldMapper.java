package ru.clevertec.mapper;

import ru.clevertec.annotation.JsonField;
import ru.clevertec.setter.*;

import java.lang.reflect.Field;
import java.util.List;

public class FieldMapper {

    public <T> void setFieldValue(T instance, String fieldName, String value, Class<?> clazz) throws Exception {
        Field field = getField(clazz, fieldName);
        if (field != null) {
            field.setAccessible(true);
            setFieldValueByType(instance, field, value);
            field.setAccessible(false);
        }
    }

    private Field getField(Class<?> clazz, String key) throws NoSuchFieldException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(key)) {
                return field;
            }

            JsonField jsonField = field.getAnnotation(JsonField.class);
            if (jsonField != null && jsonField.value().equals(key)) {
                return field;
            }
        }
        throw new NoSuchFieldException("Field with name '" + key + "' not found in class " + clazz.getName());
    }

    private <T> void setFieldValueByType(T instance, Field field, String value) throws Exception {
        List<FieldValueSetter> setters = List.of(
                new StringFieldValueSetter(),
                new IntFieldValueSetter(),
                new BooleanFieldValueSetter(),
                new ListFieldValueSetter(),
                new DefaultFieldValueSetter()
        );

        for (FieldValueSetter setter : setters) {
            if (setter.supports(field.getType())) {
                setter.setFieldValue(instance, field, value);
                return;
            }
        }
        throw new IllegalArgumentException("Unsupported field type: " + field.getType());
    }
}
