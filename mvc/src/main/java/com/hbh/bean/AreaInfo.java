package com.hbh.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by chenbin@megaeyes.com on 2018/3/6 0006.
 */
public class AreaInfo {
    private Integer id;
    private Integer shapeTypeCode;

    private String shapeTypeName;
    //区域类型代码
    private Integer areaTypeCode;
    //区域类型名称
    private String areaTypeName;
    //图标base64后的字符串数据
    private String icon;
    //区域名称
    private String name;
    //区域描述
    private String destription;
    //区域更新时间
    private String updateTime;
    //区域用途
    private String purpose;
    //坐标信息集
    private List<AreaPoint> points;
    //绘图参数集
    private List<AreaPaint> paints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(Integer areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
    }

    public String getAreaTypeName() {
        return areaTypeName;
    }

    public void setAreaTypeName(String areaTypeName) {
        this.areaTypeName = areaTypeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestription() {
        return destription;
    }

    public void setDestription(String destription) {
        this.destription = destription;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public List<AreaPoint> getPoints() {
        return points;
    }

    public void setPoints(List<AreaPoint> points) {
        this.points = points;
    }

    public List<AreaPaint> getPaints() {
        return paints;
    }

    public void setPaints(List<AreaPaint> paints) {
        this.paints = paints;
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
}
