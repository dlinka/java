package com.cr.json.jackson2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.cr.common.Facility.print;

/**
 * get方法和path方法的区别
 * path：
 * 如果不存在这样的值，节点不是对象，或者没有指定的值，则返回MissingNode，调用isMissingNode返回true
 * get：
 * 如果不存在这样的值，节点不是对象，或者没有指定的值，则返回null
 *
 * 如果值为null，则返回NullNode
 */
public class Example3 {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "{\"null\":null,\"code\":0,\"message\":\"成功\",\"data\":{\"fileUrl\":\"https://www.baidu.com\",\"fileVersionName\":\"v0.0.1\"}}";
        JsonNode jsonNode = objectMapper.readTree(result);
        print(jsonNode);

        //返回com.fasterxml.jackson.databind.node.NullNode
        JsonNode nullNode1 = jsonNode.path("null");
        print(nullNode1.getClass().getName());
        JsonNode nullNode2 = jsonNode.get("null");
        print(nullNode2.getClass().getName());

        //返回com.fasterxml.jackson.databind.node.ObjectNode
        JsonNode dataNode = jsonNode.path("data");
        print(dataNode.getClass().getName());

        //返回com.fasterxml.jackson.databind.node.MissingNode
        JsonNode pathNode = dataNode.path("path");
        print(pathNode.getClass().getName());
        //这里调用不会抛出异常
        print(pathNode.asText());

        //返回null
        JsonNode getNode = dataNode.get("get");
        print(getNode);
        //抛出NullPointerException异常
        print(getNode.asText());
    }

}
