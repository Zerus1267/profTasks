package com.example.tasks.task_three;

import com.example.tasks.task_one.Accountant;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeIntoJSON(Accountant accountant) throws IOException {
        objectMapper.enableDefaultTyping();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/com/example/tasks/task_three/test1.json"), accountant);
    }

    public static Accountant readFromJSONFile() throws IOException {
        objectMapper.enableDefaultTyping();
        return objectMapper.readValue(new File("src/main/java/com/example/tasks/task_three/test1.json"), Accountant.class);
    }
}
