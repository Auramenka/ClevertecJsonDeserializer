package ru.clevertec.deserializer;

import ru.clevertec.mapper.JsonMapper;
import ru.clevertec.parser.JsonToMapParser;

import java.util.*;

public class JsonDeserializer {

    private final JsonMapper jsonMapper = new JsonMapper();
    private final JsonToMapParser jsonToMapParser = new JsonToMapParser();

    public <T> T deserialize(String jsonString, Class<T> clazz) throws Exception {
        jsonString = jsonString.trim();

        if (jsonString.charAt(0) != '{' ||  jsonString.charAt(jsonString.length() - 1) != '}') {
            throw new IllegalArgumentException("Invalid json string");
        }

        Map<String, String> jsonMap = jsonToMapParser.parseJsonToMap(jsonString);
        return jsonMapper.mapToEntity(jsonMap, clazz);
    }
}
