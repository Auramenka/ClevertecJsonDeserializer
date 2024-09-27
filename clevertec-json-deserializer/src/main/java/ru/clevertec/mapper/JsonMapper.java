package ru.clevertec.mapper;

import java.util.Map;

public class JsonMapper {

    private final FieldMapper fieldMapper = new FieldMapper();

    public <T> T mapToEntity(Map<String, String> jsonMap, Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            fieldMapper.setFieldValue(instance, entry.getKey(), entry.getValue(), clazz);
        }

        return instance;
    }

}
