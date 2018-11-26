package me.craftcreative.bananaz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.R;

public class AddImgList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mImageUrls = new ArrayList<>();
    private final int IMG = 0, BUTTON =1;

    public AddImgList(Context mContext, List<String> mImageUrls) {
        this.mContext = mContext;
        this.mImageUrls = mImageUrls;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

                View view = inflater.inflate(R.layout.item_picture_add_view,viewGroup,false);
                viewHolder = new ViewHolder3(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

            final ViewHolder3 viewHolder3 = (ViewHolder3) viewHolder;
            //IT WILL MAKE IMAGE SMALLER not HUGE LIKE THEIR FORMAT
            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background);

            // SET UP GLADE FOR PICTURES
            Glide.with(mContext)
                    .load(mImageUrls.get(i))
                    .apply(requestOptions)
                    .into(viewHolder3.img_item);


            viewHolder3.img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(viewHolder3.getAdapterPosition());
                }
            });

            // SET UP ONimg CLICKED
            viewHolder3.img_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "" + mImageUrls.get(i), Toast.LENGTH_SHORT).show();
                }
            });
        }


    public void removeAt (int position){
        mImageUrls.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mImageUrls.size());
    }


        @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mImageUrls.get(position).equalsIgnoreCase("button")){
            return BUTTON;
        }else if (!mImageUrls.get(position).equalsIgnoreCase("button")){
            return IMG;
        }
        return -1;
    }



}
