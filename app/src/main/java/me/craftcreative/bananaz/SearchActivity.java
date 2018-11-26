package me.craftcreative.bananaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    //Top Nav
    Toolbar toolbar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Find the toolbar view inside the activity layout
        toolbar3 = (Toolbar) findViewById(R.id.toolbar);
        // Make sure the toolbar exists
        setSupportActionBar(toolbar3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        ///THE SEARCH PROCESS
        MenuItem mSearch = menu.findItem(R.id.i_s_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearch.expandActionView();
        ///SETING THE HINT
        mSearchView.setQueryHint(getResources().getString(R.string.search));
        //SET THE BACK BUTTON TO GO BACK
        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return false;
            }
            //HERE IT IS
            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
               finish();
                return false;
            }
        });
        //DO SOMETHING WITH QUERY
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
