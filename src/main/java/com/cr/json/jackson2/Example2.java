package com.cr.json.jackson2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.cr.common.Facility.print;

/**
 * 遍历json
 */
public class Example2 {

    static ObjectMapper mapper = new ObjectMapper();
    static String json =
            "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": {\n" +
            "    \"first\": \"Yong\",\n" +
            "    \"last\": \"Mook Kim\"\n" +
            "  },\n" +
            "  \"contact\": [\n" +
            "    {\n" +
            "      \"type\": \"phone/home\",\n" +
            "      \"ref\": \"111-111-1234\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"phone/work\",\n" +
            "      \"ref\": \"222-222-2222\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static void main(String[] args) throws IOException {
        JsonNode root = mapper.readTree(json);

        print(root.path("id").asLong());
        print(root.at("/name/first").asText());

        JsonNode nameNode = root.path("name");
        if (!nameNode.isMissingNode()) {
            print(nameNode.path("first").asText());
            print(nameNode.path("last").asText());
        }

        JsonNode contactNode = root.path("contact");
        if (contactNode.isArray()) {
            for (JsonNode node : contactNode) {
                print(node.path("type").asText());
                print(node.path("ref").asText());
            }
        }
    }

}
