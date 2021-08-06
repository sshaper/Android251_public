package com.ebookfrenzy.restgoogle;


import java.util.List;

public class CouponsWrapper {
    private List<Coupon> coupons;

    public List<Coupon> getCouponList() {
        return coupons;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.coupons = couponList;
    }
}
