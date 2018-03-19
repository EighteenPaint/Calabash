package com.hbh;

/**
 * Created by chenbin@megaeyes.com on 2018/3/7 0007.
 */
public class TestPO {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TestPO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
