package me.craftcreative.bananaz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.Adapters.BuyListAdapter;
import me.craftcreative.bananaz.Adapters.SellListAdapter;
import me.craftcreative.bananaz.Objects.Product;
import me.craftcreative.bananaz.R;

public class TabThreeFragment extends Fragment {

    private RecyclerView listSell;
    private SellListAdapter listAdapter;

    private static View view;
    //TEST LIST
    List<Object> product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_three,container,false);
        initViews();
        return view;
    }

    public void initViews(){
        listSell = (RecyclerView) view.findViewById(R.id.list_sell);
        product = new ArrayList<>();
        lodInfo();

        listAdapter = new SellListAdapter(view.getContext(),product);
        //Menager
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        listSell.setLayoutManager(mLinearLayoutManager);

        //Add Adapter
        listSell.setAdapter(listAdapter);

    }

    public void lodInfo(){
        product.add("button");
        product.add(new Product("Havasu Falls","subTitle1","2300$",
                "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        product.add(new Product("Trondheim","subTitle2","45$",
                "https://i.redd.it/tpsnoz5bzo501.jpg"));
        product.add(new Product("Portugals","subTitle3","50$",
                "https://i.redd.it/qn7f9oqu7o501.jpg"));
        product.add(new Product("Rocky Mountain ","subTitle4","2$",
                "https://i.redd.it/j6myfqglup501.jpg"));
        product.add(new Product("Mahahual","subTitle5","23$",
                "https://i.redd.it/0h2gm1ix6p501.jpg"));
        product.add(new Product("Frozen Lake","subTitle6","44$",
                "https://i.redd.it/k98uzl68eh501.jpg"));
        product.add(new Product("White Sands","subTitle7","50$",
                "https://i.redd.it/glin0nwndo501.jpg"));
        product.add(new Product("Austrailia","subTitle8","20$",
                "https://i.redd.it/obx4zydshg601.jpg"));
        product.add(new Product("Washington","subTitle9","200$",
                "https://i.imgur.com/ZcLLrkY.jpg"));
    }
}
