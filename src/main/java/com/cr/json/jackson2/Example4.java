package com.cr.json.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static com.cr.common.Facility.print;

/**
 * 由于JsonNode对象不可变，主要用于读取，所以使用ObjectNode进行创建Json对象图
 */
public class Example4 {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode root = objectMapper.createObjectNode();
        root.put("uid", 1L);

        ObjectNode profile = objectMapper.createObjectNode();
        profile.put("name", "engineer");
        profile.put("year", 10);
        root.set("profile", profile);

        ObjectNode school = objectMapper.createObjectNode();
        school.put("university", "east china");
        root.set("school", school);

        print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
    }

}
