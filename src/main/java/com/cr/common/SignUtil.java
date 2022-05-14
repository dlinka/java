package com.cr.common;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;

import java.util.*;

import static com.cr.common.Facility.print;

public class SignUtil {

    public static String genSign(Map<String, String> param) {
        StringBuilder builder = new StringBuilder();
        TreeMap map = new TreeMap(param);
        map.forEach((k, v) -> {
            builder.append(k).append("=").append(v).append("&");
        });
        builder.append("appSecret=iaosdfji");
        return DigestUtil.md5Hex(builder.toString());
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceId", "123");
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("appKey", "asdoifjsado");
        genSign(map);
    }
}
