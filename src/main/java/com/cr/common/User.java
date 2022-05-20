package com.cr.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class User {

    private Long uid;
    private String username;
    private Integer age;

    public User(Long uid, String username, Integer age) {
        this.uid = uid;
        this.username = username;
        this.age = age;
    }
}
