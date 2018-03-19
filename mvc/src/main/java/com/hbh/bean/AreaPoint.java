package com.hbh.bean;

/**
 * Created by chenbin@megaeyes.com on 2018/3/6 0006.
 */
public class AreaPoint {
    private Integer id;
    private Integer shapeTypeCode;
    private String shapeTypeName;
    //区域ID
    private Integer areaId;
    //X坐标
    private String x;
    //Y坐标
    private String y;
    //R 半径，类型为圆形区域的时候有用
    private String r;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShapeTypeCode() {
        return shapeTypeCode;
    }

    public void setShapeTypeCode(Integer shapeTypeCode) {
        this.shapeTypeCode = shapeTypeCode;
    }

    public String getShapeTypeName() {
        return shapeTypeName;
    }

    public void setShapeTypeName(String shapeTypeName) {
        this.shapeTypeName = shapeTypeName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
