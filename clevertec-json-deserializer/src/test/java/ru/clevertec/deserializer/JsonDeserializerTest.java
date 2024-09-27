package ru.clevertec.deserializer;

import org.junit.jupiter.api.Test;
import ru.clevertec.model.Hobby;
import ru.clevertec.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    @Test
    public void testValidJsonDeserialization() throws Exception {
        String json = "{\"full_name\":\"Hanna\", \"age\": 18, \"address\": {\"street\": \"Gorkogo\", \"postal_code\": \"246014\"}, \"married\": true, \"hobbies\":[{\"name\": \"Swimming\"}]}";
        Person person = new JsonDeserializer().deserialize(json, Person.class);

         List<Hobby> hobbies = person.getHobbies();

        assertEquals("Hanna", person.getName());
        assertEquals(1, hobbies.size());
        assertEquals("Gorkogo", person.getAddress().getStreet());
        assertEquals("246014", person.getAddress().getPostalCode());
        assertEquals(List.of("Swimming"), hobbies);
    }

    @Test
    public void testInvalidJsonThrowException() {
        String json = "\"full_name\":\"Hanna\", \"age\": 18, \"address\": {\"street\": \"Gorkogo\", \"postal_code\": \"246014\"}, \"married\": true, \"hobbies\":[{\"name\": \"Swimming\"}]}";
        assertThrows(IllegalArgumentException.class, () -> new JsonDeserializer().deserialize(json, Person.class));
    }
}