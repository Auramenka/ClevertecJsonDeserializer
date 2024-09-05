package ru.clevertec;

import ru.clevertec.deserializer.JsonDeserializer;
import ru.clevertec.model.Person;


public class Main {
     public static void main(String[] args) throws Exception {
         String json = "{\"full_name\":\"Hanna\", \"age\": 18, \"address\": {\"street\": \"Gorkogo\", \"postal_code\": \"246014\"}, \"married\": true, \"hobbies\":[{\"name\": \"Swimming\"}]}";
         Person person = new JsonDeserializer().deserialize(json, Person.class);
         System.out.println(person);
    }
}