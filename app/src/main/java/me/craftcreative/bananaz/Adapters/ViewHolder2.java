package me.craftcreative.bananaz.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import me.craftcreative.bananaz.R;

public class ViewHolder2 extends RecyclerView.ViewHolder{

    ImageButton img_btn_add;

    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        this.img_btn_add = (ImageButton) itemView.findViewById(R.id.btn_add_pics);
    }
}
