package com.cr.common;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.*;

import static com.cr.common.Facility.print;

public class SignUtil {

    public static String genSign(Map<String, Object> param) {
        StringBuilder builder = new StringBuilder();
        TreeMap map = new TreeMap(param);
        map.forEach((k, v) -> {
            builder.append(k).append("=").append(v).append("&");
        });
        builder.append("appSecret=iaosdfji");
        return DigestUtil.md5Hex(builder.toString());
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", 123L);
        map.put("timestamp", System.currentTimeMillis());
        map.put("appKey", "asdoifjsado");
        map.put("sign", genSign(map));


        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        print(jsonObject.toString());

    }
}
