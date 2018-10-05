package com.genericyzh.miaosha.common.result;

import java.io.Serializable;
import java.util.List;

/**
 * 一般用于service层返回
 *
 * @author genericyzh
 */
public class PageInfo<T> implements Serializable {
    protected long total;

    protected List<T> datas;

    public PageInfo() {
    }

    public PageInfo(long total, List<T> datas) {
        this.total = total;
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "PageInfo2{" +
                "total=" + total +
                ", datas=" + datas +
                '}';
    }

    public long getTotal() {
        return this.total;
    }

    public PageInfo setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public PageInfo setDatas(List<T> datas) {
        this.datas = datas;
        return this;
    }

}
