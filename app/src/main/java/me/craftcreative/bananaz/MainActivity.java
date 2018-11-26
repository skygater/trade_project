package me.craftcreative.bananaz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.Adapters.StaggerdAdapter;
import me.craftcreative.bananaz.Fragments.Login_Fragment;
import me.craftcreative.bananaz.Fragments.TabOneFragment;
import me.craftcreative.bananaz.Tweaks.UtilsLogIn;

public class MainActivity extends AppCompatActivity {

    //Number of colums in Staggerd View
    private static final int NUM_COLUMNS = 2;
    //Dummy Test LIST for Adapter
    private List<String> mImageUrls = new ArrayList<>();
    private  List<String> mNames = new ArrayList<>();
    //Recycle view adapters
    RecyclerView recyclerView;
    StaggerdAdapter adapter;
    //Bottom Nav
    BottomNavigationViewEx bottomToolBar;
    FloatingActionButton fab;
    //Top Nav
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Make sure the toolbar exists
        setSupportActionBar(toolbar);
        initNavTop();

        //INIT DummyTest and RECYCLE VIEW
        initImageBitmaps();

        // Bottom Nav
        bottomToolBar =(BottomNavigationViewEx) findViewById(R.id.bnve);
        bottomToolBar.enableAnimation(false);
        bottomToolBar.enableShiftingMode(false);
        bottomToolBar.enableItemShiftingMode(false);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        initEvent ();

    }
    //TEST for STAGGERD view
    private void initImageBitmaps() {

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");

        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");

        //INIT RECYCLE VIEW METHOD
        initRecycleView();
    }

    // INITIALIZE Recycle View
    private void initRecycleView(){
        //INIT recyvleView and ADAPTER
        recyclerView = findViewById(R.id.main_product_list);
        adapter = new StaggerdAdapter(this,mNames,mImageUrls);
        //StaggerdMenager INIT
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        //SET UPP MENAGER and ADAPTER
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);


        // HIDE THE BOTTOM NAV BAR WHEN SCROLLING DOWN
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
               if(recyclerView.SCROLL_STATE_IDLE == newState){
//                   bottomToolBar.setVisibility(View.VISIBLE);
//                   Animation slideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up);
//                   bottomToolBar.startAnimation(slideUp);
               }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                   bottomToolBar.setVisibility(View.INVISIBLE);
                    // Scrolling UP
                } else {
                    bottomToolBar.setVisibility(View.VISIBLE);
                    // slide-up animation
                    Animation slideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up);
                    bottomToolBar.startAnimation(slideUp);
//                    // Scrolling down
                }

            }
        });
    }

    //INIT onClick listener for BOTTOM NAV
    private void initEvent (){
        bottomToolBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int position = 0;
                switch (menuItem.getItemId()) {
                    case R.id.i_products:
                        position = 0;
                        Toast.makeText(MainActivity.this, "HELLO"+position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.i_profile:
                        position = 1;
                        //Toast.makeText(MainActivity.this, "HELLO"+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.i_empty: {
                        return false;
                    }
                }

                if(previousPosition != position) {
                    previousPosition = position;
                }

                return true;
            }
        });
        //Central FAV Button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, "HELLO FROM CENTER", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AddProductActivity.class);
                startActivity(intent);
            }
        });
    }



    //INIT OnCLick Top nav
    private void initNavTop(){

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int position;
                switch (menuItem.getItemId()) {
                    case R.id.i_search:
                        position = 0;
                        Intent search = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(search);
                        //Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.i_filter:
                        position = 1;
                       // Toast.makeText(MainActivity.this, "Filter", Toast.LENGTH_SHORT).show();
                        Intent filter = new Intent(MainActivity.this,FilterActivity.class);
                        startActivity(filter);
                        break;
                }
                return true;
            }
        });

    }


    // INITIALIZE Menu and ADDing Menu icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
