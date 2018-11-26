package me.craftcreative.bananaz.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import me.craftcreative.bananaz.R;

public class ViewHolder3 extends RecyclerView.ViewHolder {

    public ImageView img_item;
    public ImageButton img_del;


    public ViewHolder3(@NonNull View itemView) {
        super(itemView);
        this.img_item = itemView.findViewById(R.id.product_item_img);
        this.img_del = itemView.findViewById(R.id.delete_this_pic);
    }
}