package ru.clevertec.setter;

import ru.clevertec.deserializer.JsonDeserializer;

import java.lang.reflect.Field;

public class DefaultFieldValueSetter implements FieldValueSetter<Object> {

    @Override
    public void setFieldValue(Object instance, Field field, String value) throws Exception {
        field.set(instance, deserialize(value, field.getType()));
    }

    @Override
    public boolean supports(Class<?> fieldType) {
        return true;
    }

    private <T> T deserialize(String value, Class<T> clazz) throws Exception {
        return (T) new JsonDeserializer().deserialize(value, clazz);
    }
}
