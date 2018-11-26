package me.craftcreative.bananaz.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import me.craftcreative.bananaz.R;

public class ViewHolder1 extends RecyclerView.ViewHolder{

    Button btn1,btn2;

    public ViewHolder1(@NonNull View itemView) {
        super(itemView);
        this.btn2 = (Button) itemView.findViewById(R.id.btn_bought) ;
        this.btn1 = (Button) itemView.findViewById(R.id.btn_fav) ;
    }
}
