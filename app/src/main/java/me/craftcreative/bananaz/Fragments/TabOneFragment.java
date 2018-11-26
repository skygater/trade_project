package me.craftcreative.bananaz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.Adapters.StaggerdAdapter;
import me.craftcreative.bananaz.R;

public class TabOneFragment extends Fragment {

    //Number of colums in Staggerd View
    private static final int NUM_COLUMNS = 2;
    public RecyclerView fav_list;
    public StaggerdAdapter staggerdAdapter;
    public List<String>img_url,title;

    public static View view;

    //INIT AND INFLATE THE LAYOUT!
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_one,container,false);
       initViews();
        return view;
    }

    public void initViews(){

        fav_list = (RecyclerView) view.findViewById(R.id.fav_list);
        img_url = new ArrayList<>();
        title = new ArrayList<>();
        loadData();

        //Menagers
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        fav_list.setLayoutManager(staggeredGridLayoutManager);
        staggerdAdapter =new StaggerdAdapter(getContext(),title,img_url);
        fav_list.setAdapter(staggerdAdapter);
    }

    public void loadData (){

        img_url.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        title.add("Havasu Falls");

        img_url.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        title.add("Trondheim");

        img_url.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        title.add("Portugal");

        img_url.add("https://i.redd.it/j6myfqglup501.jpg");
        title.add("Rocky Mountain National Park");

        img_url.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        title.add("Mahahual");

        img_url.add("https://i.redd.it/k98uzl68eh501.jpg");
        title.add("Frozen Lake");

        img_url.add("https://i.redd.it/glin0nwndo501.jpg");
        title.add("White Sands Desert");

        img_url.add("https://i.redd.it/obx4zydshg601.jpg");
        title.add("Austrailia");

        img_url.add("https://i.imgur.com/ZcLLrkY.jpg");
        title.add("Washington");
    }


}
