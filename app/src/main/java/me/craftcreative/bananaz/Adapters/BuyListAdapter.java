package me.craftcreative.bananaz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import me.craftcreative.bananaz.Objects.Product;
import me.craftcreative.bananaz.R;

public class BuyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int PRODUCT= 0, BUTTON = 1;
    //JustFOR Testing
    private Context mContext;
    private List<Object> product;

    //CONSTRUCTOR FOR ADAPTER
    public BuyListAdapter(Context mContext, List<Object> product) {
        this.mContext = mContext;
        this.product = product;

    }
    //Set Where to inflate
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (i){
            case PRODUCT:
                View view =inflater.inflate(R.layout.product_profile_item,viewGroup,false);
                viewHolder = new ViewHolder0(view);
                break;
            case BUTTON:
                View view1 = inflater.inflate(R.layout.item_buttons_list,viewGroup,false);
                viewHolder = new ViewHolder1(view1);
                break;
            default:
                View view2 =inflater.inflate(R.layout.product_profile_item,viewGroup,false);
                viewHolder = new ViewHolder0(view2);
                break;
        }

       // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_profile_item,viewGroup,false);
        return  viewHolder;
    }


    //FILLING INFO;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  viewHolder, int i) {
        // IT WILL MAKE IMAGE SMALLER not HUGE LIKE THEIR FORMAT

        switch (viewHolder.getItemViewType()){
            case PRODUCT:
                ViewHolder0 viewHolder1 = (ViewHolder0) viewHolder;
                Product p = (Product) product.get(i);

                RequestOptions requestOptions = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background);
                //SET GLIDE
                Glide.with(mContext)
                        .load(p.getImg_product())
                        .apply(requestOptions)
                        .into(viewHolder1.product_img);
                //SetText
                viewHolder1.product_name.setText(p.getTitle());
                viewHolder1.product_sub.setText(p.getSubtitle());
                viewHolder1.product_price.setText(p.getPrice());
                break;
            case BUTTON:
                ViewHolder1 viewHolder2 = (ViewHolder1) viewHolder;
                break;
        }


    }

    @Override
    public int getItemCount() {
        return product.size();
    }


    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {

        if(product.get(position) instanceof Product){
            return PRODUCT;
        }else if(product.get(position) instanceof String){
            return BUTTON;
        }
       return -1;
    }


}

