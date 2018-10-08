package com.genericyzh.miaosha.good.model.vo;


import com.genericyzh.miaosha.user.model.UserInfo;

import java.math.BigDecimal;
import java.util.Date;

public class GoodDetailVO {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private MiaoshaGoodDetail good;
    private UserInfo user;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public MiaoshaGoodDetail getGood() {
        return good;
    }

    public void setGood(MiaoshaGoodDetail good) {
        this.good = good;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public static class MiaoshaGoodDetail {
        private String id;
        private String goodName;
        private String goodImg;
        private Date startDate;
        private Date endDate;
        private BigDecimal goodPrice;
        private BigDecimal miaoshaPrice;
        private int stockCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getGoodImg() {
            return goodImg;
        }

        public void setGoodImg(String goodImg) {
            this.goodImg = goodImg;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public BigDecimal getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(BigDecimal goodPrice) {
            this.goodPrice = goodPrice;
        }

        public BigDecimal getMiaoshaPrice() {
            return miaoshaPrice;
        }

        public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
            this.miaoshaPrice = miaoshaPrice;
        }

        public int getStockCount() {
            return stockCount;
        }

        public void setStockCount(int stockCount) {
            this.stockCount = stockCount;
        }
    }
}
