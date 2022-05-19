package com.cr.json.jackson2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.printLine;

/**
 * json和对象、集合等互转
 */
public class Example1 {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        /**
         * 序列化忽略null的字段
         */
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        /**
         * 忽略没有匹配的JSON字段
         */
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) throws IOException {
        convertObjectToJson();
        printLine();
        convertJsonToObject();
        printLine();
        convertJsonToList();
        printLine();
        convertJsonToMap();
    }

    static void convertObjectToJson() throws JsonProcessingException {
        Staff staff = Staff.createStaff();
        print(mapper.writeValueAsString(staff));
        print(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff));
    }

    static void convertJsonToObject() throws IOException {
        String json = "{\"name\":\"cr\",\"age\":27,\"position\":[\"Founder\",\"Programmer\",\"Writer\"],\"skills\":[\"java\",\"python\",\"node\",\"kotlin\"],\"salary\":{\"2018\":14000,\"2012\":12000,\"2010\":10000}}";
        Staff staff = mapper.readValue(json, Staff.class);
        print(staff);
    }

    static void convertJsonToList() throws IOException {
        List<Staff> list = new ArrayList<>();
        list.add(Staff.createStaff());
        String jsonList = mapper.writeValueAsString(list);
        print(jsonList);

        list = mapper.readValue(jsonList, new TypeReference<List<Staff>>() {
        });
        print(list);

        list = Arrays.asList(mapper.readValue(jsonList, Staff[].class));
        print(list);
    }

    static void convertJsonToMap() throws IOException {
        Map<String, String> stringMap = mapper.readValue("{\"name\":\"cr\", \"age\":\"27\"}", Map.class);
        print(stringMap);

        Map<String, Staff> map = new HashMap<>();
        map.put("cr", Staff.createStaff());
        String json = mapper.writeValueAsString(map);
        print(json);
        map = mapper.readValue(json, new TypeReference<Map<String, Staff>>() {
        });
        print(map.get("cr").getName());
    }

}
