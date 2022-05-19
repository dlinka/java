package com.cr.json.jackson2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
/**
 * @JsonInclude：表示序列化的时候忽略null属性
 * 注解在类上对所有属性生效
 * 注解在某个属性上，表示只忽略这个属性
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Staff {

    /**
     * @JsonProperty：表示这个属性序列化和反序列化的名字
     */
    @JsonProperty("name")
    private String name;
    private String[] position;
    private List<String> skills;
    private Map<String, BigDecimal> salary;

    /**
     * @JsonIgnore：表示序列话或者反序列化忽略这个属性
     */
    @JsonIgnore
    private int age;

    public static Staff createStaff() {
        Staff staff = new Staff();
        staff.setName("cr");
        staff.setAge(27);
        staff.setPosition(new String[]{"Founder", "Programmer", "Writer"});
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        return staff;
    }

}

