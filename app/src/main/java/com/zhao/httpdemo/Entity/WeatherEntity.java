package com.zhao.httpdemo.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhao on 2016/9/23.
 */

public class WeatherEntity implements Serializable {

    /**
     * province_cn : 湖北
     * district_cn : 武汉
     * name_cn : 黄陂
     * name_en : huangpi
     * area_id : 101200103
     */

    @SerializedName("province_cn")
    private String province;
    @SerializedName("district_cn")
    private String district;
    @SerializedName("name_cn")
    private String name;
    @SerializedName("name_en")
    private String nameEn;
    @SerializedName("area_id")
    private String areaId;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", areaId='" + areaId + '\'' +
                '}';
    }
}
