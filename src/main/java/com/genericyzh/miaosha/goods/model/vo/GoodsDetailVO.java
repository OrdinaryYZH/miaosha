package com.genericyzh.miaosha.goods.model.vo;


import com.genericyzh.miaosha.user.model.UserInfo;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsDetailVO {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private MiaoshaGoodsDetail goods;
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

    public MiaoshaGoodsDetail getGoods() {
        return goods;
    }

    public void setGoods(MiaoshaGoodsDetail goods) {
        this.goods = goods;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public static class MiaoshaGoodsDetail {
        private String id;
        private String goodsName;
        private String goodsImg;
        private Date startDate;
        private Date endDate;
        private BigDecimal goodsPrice;
        private BigDecimal miaoshaPrice;
        private int stockCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
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

        public BigDecimal getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(BigDecimal goodsPrice) {
            this.goodsPrice = goodsPrice;
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
