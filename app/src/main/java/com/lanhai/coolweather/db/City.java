package com.lanhai.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Zhanglinlin on 2017/10/8 0008.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public City setId(int id) {
        this.id = id;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public City setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public int getCityCode() {
        return cityCode;
    }

    public City setCityCode(int cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public City setProvinceId(int provinceId) {
        this.provinceId = provinceId;
        return this;
    }
}
