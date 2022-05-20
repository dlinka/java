package com.cr.fastjson;

import com.alibaba.fastjson.JSON;
import com.cr.common.User;

public class FastJsonDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setUid(27L);
        user.setAge(18);
        user.setUsername("CR");

        String s = JSON.toJSONString(user);
        System.out.println(s);
    }

}
