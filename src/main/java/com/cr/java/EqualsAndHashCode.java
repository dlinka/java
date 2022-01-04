package com.cr.java;

import lombok.Data;

@Data
public class EqualsAndHashCode {
    private int userId;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EqualsAndHashCode)) return false;
        EqualsAndHashCode user = (EqualsAndHashCode) obj;
        return (this.getName().equals(user.getName())) && (this.getUserId() == user.getUserId());
    }

    //设计hashCode方法要避免哈希冲突
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + userId;
        result = result * 31 + name.hashCode();
        return result;
    }
}
