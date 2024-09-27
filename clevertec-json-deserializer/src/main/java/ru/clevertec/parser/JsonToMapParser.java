package ru.clevertec.parser;

import java.util.HashMap;
import java.util.Map;

public class JsonToMapParser {

    public Map<String, String> parseJsonToMap(String jsonString) {
        Map<String, String> jsonMap = new HashMap<>();

        jsonString = jsonString.trim();
        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();

        String[] keyValuePairs = jsonString.split(",(?![^{}\\[\\]]*\\}|[^{}\\[\\]]*\\])");

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":", 2);

            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Error: There must be 2 elements in a key-value pair. Pair: " + pair);
            }

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim();

            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            jsonMap.put(key, value);
        }
        return jsonMap;
    }
}
