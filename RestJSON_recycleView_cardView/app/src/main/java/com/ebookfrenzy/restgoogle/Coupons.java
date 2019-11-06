package com.ebookfrenzy.restgoogle;

import java.util.ArrayList;

public class Coupons {
    private String store;
    private String coupon;
    private String expiryDate;
    private String couponCode;
    private String category;

    private ArrayList<Coupons> coupons;
    public ArrayList<Coupons> getCouponList() {
        return coupons;
    }

    //METHODS FOR GETTING AND SETTING EACH COUPON
    public String getStore() {
        return store;
    }
    public void setStore(String store) {
        this.store = store;
    }

    public String getCoupon() {
        return coupon;
    }
    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCategory() {
        return category;
    }
    public void setCouponCategory(String couponCode) {
        this.category = category;
    }

}
