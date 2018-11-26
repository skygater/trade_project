package me.craftcreative.bananaz;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import me.craftcreative.bananaz.Adapters.TabAdapter;
import me.craftcreative.bananaz.Fragments.Login_Fragment;
import me.craftcreative.bananaz.Fragments.TabFourFragment;
import me.craftcreative.bananaz.Fragments.TabOneFragment;
import me.craftcreative.bananaz.Fragments.TabThreeFragment;
import me.craftcreative.bananaz.Fragments.TabTwoFragment;
import me.craftcreative.bananaz.Tweaks.UtilsLogIn;

public class ProfileActivity extends AppCompatActivity {

    //Top Nav
    Toolbar f_toolbar;

    //LOG IN Fragments
    FrameLayout frameLayout;
    int isLoggedIn = 1;
    private static FragmentManager fragmentManager;
    //TABS Fragment
    TabAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    //JSON STRING
    String JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        if(isLoggedIn == 0) {
            //Fragments LOG IN
            fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Login_Fragment(), UtilsLogIn.Login_Fragment).commit();
        }

        //Fragments TABS
        //INIT VIEW PAGER and TAB LAYOUT
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabtoolbar);
        //Init ADAPTER
        adapter = new TabAdapter(getSupportFragmentManager());
        //FIll the addapter with fragments!
        adapter.addFragment(new TabOneFragment(),"LIKED");
        adapter.addFragment(new TabTwoFragment(),"BUYING");
        adapter.addFragment(new TabThreeFragment(),"SELLING");
        adapter.addFragment(new TabFourFragment(),"PROFILE");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    ///FOR LOG IN FRAGMENTS
    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_exit)
                .replace(R.id.frameContainer, new Login_Fragment(),
                        UtilsLogIn.Login_Fragment).commit();
    }



    @Override
    public void onBackPressed() {
        if(isLoggedIn == 0) {
            // Find the tag of signup and forgot password fragment
            Fragment SignUp_Fragment = fragmentManager
                    .findFragmentByTag(UtilsLogIn.SignUp_Fragment);
            Fragment ForgotPassword_Fragment = fragmentManager
                    .findFragmentByTag(UtilsLogIn.ForgotPassword_Fragment);

            // Check if both are null or not
            // If both are not null then replace login fragment else do backpressed
            // task

            if (SignUp_Fragment != null)
                replaceLoginFragment();
            else if (ForgotPassword_Fragment != null)
                replaceLoginFragment();
            else
                super.onBackPressed();
        }
        super.onBackPressed();
    }

    //INIT TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }
}
