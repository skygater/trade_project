package me.craftcreative.bananaz.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.craftcreative.bananaz.R;

public class ViewHolder0 extends RecyclerView.ViewHolder{

    ImageView product_img;
    TextView product_name, product_sub,product_price;

    public ViewHolder0(@NonNull View itemView) {
        super(itemView);

        this.product_img = (ImageView) itemView.findViewById(R.id.product_profile_img);
        this.product_name = (TextView) itemView.findViewById(R.id.product_profile_name);
        this.product_sub = (TextView) itemView.findViewById(R.id.product_profile_subtitle);
        this.product_price = (TextView) itemView.findViewById(R.id.product_profile_price);
    }
}
