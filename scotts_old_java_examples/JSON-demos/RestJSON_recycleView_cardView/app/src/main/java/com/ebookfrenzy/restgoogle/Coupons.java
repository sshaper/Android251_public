package com.ebookfrenzy.restgoogle;

import java.util.ArrayList;

public class Coupons {
    private String store;
    private String coupon;
    private String expiryDate;
    private String couponCode;
    private String category;

    //THIS IS AN ARRAY LIST THAT CONTAINS ALL THE CARD DATA
    private ArrayList<Coupons> coupons;

    public ArrayList<Coupons> getCouponList() {
        return coupons;
    }

    //METHODS FOR GETTING EACH COUPON
    public String getStore() {
        return store;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getCouponCategory() {
        return category;
    }


}
