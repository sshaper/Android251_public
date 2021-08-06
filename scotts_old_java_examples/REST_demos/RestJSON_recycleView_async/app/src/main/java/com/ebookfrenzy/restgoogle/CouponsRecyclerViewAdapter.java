package com.ebookfrenzy.restgoogle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CouponsRecyclerViewAdapter extends RecyclerView.Adapter<CouponsRecyclerViewAdapter.ViewHolder> {

    private List<Coupon> couponsList;
    private Context context;

    public CouponsRecyclerViewAdapter(List<Coupon> cLst, Context ctx) {
        couponsList = cLst;
        context = ctx;
    }

    @Override
    public CouponsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gson_item, parent, false);

        CouponsRecyclerViewAdapter.ViewHolder viewHolder = new CouponsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CouponsRecyclerViewAdapter.ViewHolder holder, int position) {
        Coupon coupon = couponsList.get(position);
        holder.couponTv.setText(coupon.getCoupon());
        holder.expiryDateTv.setText(coupon.getExpiryDate());
        holder.couponCodeTv.setText(coupon.getCouponCode());

        //images are stored in drawable resources folder, using store name get image id
        //int id = context.getResources().getIdentifier(coupon.getStore(), "drawable", context.getPackageName());
        //holder.storeImg.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return couponsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView couponTv;
        public TextView expiryDateTv;
        public TextView couponCodeTv;
        //public ImageView storeImg;

        public ViewHolder(View view) {
            super(view);
            couponTv = (TextView) view.findViewById(R.id.coupon_txt);
            expiryDateTv = (TextView) view.findViewById(R.id.coupon_expiry);
            couponCodeTv = (TextView) view.findViewById(R.id.coupon_code);
            //storeImg = (ImageView) view.findViewById(R.id.imageView);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You clicked coupons at  "+ getAdapterPosition(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
