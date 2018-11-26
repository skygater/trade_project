package me.craftcreative.bananaz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import me.craftcreative.bananaz.Objects.Product;
import me.craftcreative.bananaz.R;

public class SellListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int PRODUCT = 0, BUTTON = 1;

    //Just for Testing
    private Context mContext;
    private List<Object> product;

    public SellListAdapter(Context mContext, List<Object> product) {
        this.mContext = mContext;
        this.product = product;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        /// WE choose what layput to inflate
        switch (i){
            case PRODUCT:
                View view = inflater.inflate(R.layout.product_profile_item,viewGroup,false);
                viewHolder = new ViewHolder0(view);
                break;
            case BUTTON:
                View view1 = inflater.inflate(R.layout.item_buttons_list, viewGroup,false);
                viewHolder = new ViewHolder1(view1);
                break;
            default:
                View view2 = inflater.inflate(R.layout.product_profile_item,viewGroup,false);
                viewHolder = new ViewHolder0(view2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()){
            case PRODUCT:
                ViewHolder0 viewHolder0 = (ViewHolder0) viewHolder;
                Product p = (Product) product.get(i);
                RequestOptions requestOptions = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background);
                //SET GLIDE
                Glide.with(mContext)
                        .load(p.getImg_product())
                        .apply(requestOptions)
                        .into(viewHolder0.product_img);
                //SetText
                viewHolder0.product_name.setText(p.getTitle());
                viewHolder0.product_sub.setText(p.getSubtitle());
                viewHolder0.product_price.setText(p.getPrice());
                break;
            case BUTTON:
                ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
                viewHolder1.btn1.setText(mContext.getString(R.string.tab_three_button_selling));
                viewHolder1.btn2.setText(mContext.getString(R.string.tab_three_button_sold));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    //Setting to descover what is it going next
    @Override
    public int getItemViewType(int position) {
        if(product.get(position) instanceof Product){
            return PRODUCT;
        }else if (product.get(position) instanceof String){
            return BUTTON;
        }
        return -1;
    }
}
