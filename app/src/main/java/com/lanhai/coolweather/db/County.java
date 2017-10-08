package com.lanhai.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Zhanglinlin on 2017/10/8 0008.
 */

public class County extends DataSupport {
    private int id;
    private String countyName;
    private String weatherId;
    private int cityId;

    public int getId() {
        return id;
    }

    public County setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountyName() {
        return countyName;
    }

    public County setCountyName(String countyName) {
        this.countyName = countyName;
        return this;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public County setWeatherId(String weatherId) {
        this.weatherId = weatherId;
        return this;
    }

    public int getCityId() {
        return cityId;
    }

    public County setCityId(int cityId) {
        this.cityId = cityId;
        return this;
    }
}
