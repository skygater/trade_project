package me.craftcreative.bananaz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.R;

public class StaggerdAdapter extends RecyclerView.Adapter<StaggerdAdapter.ViewHolder> {

    private static final String TAG = "StaggerdViewAd";

    //List for image and text --- IT should be LIST OF PRODUCT OBJECT
    private List<String> mNames = new ArrayList<>();
    private List<String> mImagesUrls = new ArrayList<>();
    private Context mContext;

    //CONSTRUCTOR
    public StaggerdAdapter(Context mContext,List<String> mNames, List<String> mImagesUrls) {
        this.mNames = mNames;
        this.mImagesUrls = mImagesUrls;
        this.mContext = mContext;
    }

    //SET WHERE We INFLATE
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_grid_item,viewGroup,false);

        return new ViewHolder(view);
    }

    //SET UP ITEM with INFO
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViwer");

        // IT WILL MAKE IMAGE SMALLER not HUGE LIKE THEIR FORMAT
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        // SET UP GLADE FOR PICTURES
        Glide.with(mContext)
                .load(mImagesUrls.get(i))
                .apply(requestOptions)
                .into(viewHolder.imageView);

        //SET UP TEXT
        viewHolder.textView.setText(mNames.get(i));

        // SET UP ONimg CLICKED
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, ""+mNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //SET UP SIZE OF ITEMS
    @Override
    public int getItemCount() {
        return mNames.size();
    }

    // RECYCLE VIEW VIEW HOLDER == HERE YOU CONNECT ITEM.layout
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.product_img);
            this.textView = itemView.findViewById(R.id.product_txt);
        }
    }

}
