package com.lanhai.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Zhanglinlin on 2017/10/8 0008.
 *
 */

public class Province extends DataSupport {
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public Province setId(int id) {
        this.id = id;
        return this;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Province setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public Province setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }
}
