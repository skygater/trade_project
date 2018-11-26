package me.craftcreative.bananaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.etiennelawlor.discreteslider.library.ui.DiscreteSlider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.craftcreative.bananaz.Tweaks.RecursiveRadioGroup;

public class FilterActivity extends AppCompatActivity {

    //Top Nav
    Toolbar f_toolbar;

    //Filter Date
    TextView f_date;
    //Filter Sort
    RecursiveRadioGroup fRadioGroup;
    RadioButton fRadioButton;
    //Filter Map
    TextView f_map;
    //Filter Category
    TextView f_cat;
    List<String> cat_list;
    LinearLayout cat_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //TOOLBAR
        // Find the toolbar view inside the activity layout
        f_toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Make sure the toolbar exists
        setSupportActionBar(f_toolbar);
        f_toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            //TOOLbar on click BACK
        f_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MAYBE INTENT ADD TO GO BACK TO PREVIUS FILTER SEARCH
                finish();
            }
        });

        //INIT filterDate
        initFilterDate();
        //INIT filterSort
        fRadioGroup = (RecursiveRadioGroup) findViewById(R.id.filter_radio_group);
        //INIT filterMap
        initFilterRadius();
        //INIT fileteCat
        cat_list = new ArrayList<>();
        f_cat = (TextView) findViewById(R.id.filter_category);
        cat_layout = (LinearLayout) findViewById(R.id.cat_layout);


    }


    //INIT filter Category
    //INIT filter Category ADD TO LIST and MAKE VISIBLE BAR
    public void visibilityBar(){
        if(cat_list.size() >0) {
            if (cat_layout.getVisibility() != View.VISIBLE) {
                cat_layout.setVisibility(View.VISIBLE);
            }
            String list = "";
            for (String a : cat_list) {
                list = list + a + " ";
            }
            f_cat.setText(list);
        }else{
            cat_layout.setVisibility(View.GONE);
        }
    }
    //INIT filter Category DELETE FROM LIST
    public void deleteFromList(String name ){
        Iterator<String> iter = cat_list.iterator();
        while (iter.hasNext()){
            String a = iter.next();
            if (a.equalsIgnoreCase(name)){
                iter.remove();
            }
        }
    }
    //INIT filter Category GET ID and on click add or remove from list
    public void fCheckedBox(View view){
        boolean checked = ((CheckBox) view).isChecked();
        CheckBox checkBox = (CheckBox) findViewById(view.getId());
        if(checked){
            cat_list.add(checkBox.getText().toString());
            visibilityBar();
        }else{
            deleteFromList(checkBox.getText().toString());
            visibilityBar();
        }

    }

    //INIT filter Distance Radius
    public void initFilterRadius(){
        f_map = (TextView) findViewById(R.id.map_filter);
        DiscreteSlider d = (DiscreteSlider) findViewById(R.id.discreteslider_map);
        d.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                //Toast.makeText(FilterActivity.this, "Position"+position, Toast.LENGTH_SHORT).show();
                f_map.setText(position+"");
            }
        });
    }

    //INIT get Checked button - Stack Overflow @lostdev solution and Ivan Kušt - RecursiveRadioGroup
    public void fChecked(View view){
        int radioId = fRadioGroup.getCheckedItemId();
        fRadioButton = (RadioButton) findViewById(radioId) ;
        Toast.makeText(this, "Selected " + fRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }


    //INIT filter Date
    public void initFilterDate(){
        f_date = (TextView) findViewById(R.id.date_filter);
        DiscreteSlider d = (DiscreteSlider) findViewById(R.id.discreteslider);
        d.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                //Toast.makeText(FilterActivity.this, "Position"+position, Toast.LENGTH_SHORT).show();
                f_date.setText(position+"");
            }
        });
    }
    //INIT TOOLBAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }


}
