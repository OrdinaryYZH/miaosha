package com.genericyzh.miaosha.common.query;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author genericyzh
 */
public class PageQuery implements Serializable {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
