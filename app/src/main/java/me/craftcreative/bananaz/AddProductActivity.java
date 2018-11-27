package me.craftcreative.bananaz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import me.craftcreative.bananaz.Fragments.ForgotPassword_Fragment;
import me.craftcreative.bananaz.Fragments.StepOneFragment;
import me.craftcreative.bananaz.Fragments.StepThreeFragment;
import me.craftcreative.bananaz.Fragments.StepTwoFragment;
import me.craftcreative.bananaz.Tweaks.UtilsLogIn;
import me.craftcreative.bananaz.Tweaks.UtilsProduct;

public class AddProductActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    //STEPS
    StepView stepView;
    private int currentStep = 0;
    //Fragment
    private static FragmentManager fragmentManager;
    FrameLayout frameLayout;
    RelativeLayout rel_map;
    //View Init
    Button btn;
    //Map constant
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap gMap;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        //InitVIew
        btn = (Button) findViewById(R.id.btn_one_back);

        //INIT STEPS
        stepView = (StepView) findViewById(R.id.stepview);
        List<String> name = new ArrayList<>();
        name.add(getString(R.string.step_one_title));
        name.add(getString(R.string.step_two_title));
        name.add(getString(R.string.step_three_title));
        stepView.setSteps(name);

        //FRAGMENTS
        frameLayout = (FrameLayout) findViewById(R.id.frame_product);
        rel_map = (RelativeLayout) findViewById(R.id.map_view);

        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_product, new StepOneFragment(), UtilsProduct.Step_One)
                .commit();

        // MAP test
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public int fragmentId = 0;

    //CHANGE FRAGMENT 1-3
    public void fragmentChange(int anim1, int anim2) {

        switch (currentStep) {
            case 0:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1, anim2)
                        .replace(R.id.frame_product, new StepOneFragment(),
                                UtilsProduct.Step_One).commit();
                fragmentId = 0;
                break;
            case 1:
                frameLayout.setVisibility(View.GONE);
                rel_map.setVisibility(View.VISIBLE);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1, anim2)
                        .replace(R.id.frame_product, new StepTwoFragment(),
                                UtilsProduct.Step_Two).commit();
                fragmentId = 1;
                break;
            case 2:
                frameLayout.setVisibility(View.VISIBLE);
                rel_map.setVisibility(View.GONE);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1, anim2)
                        .replace(R.id.frame_product, new StepThreeFragment(),
                                UtilsProduct.Step_Three).commit();
                fragmentId = 2;
                break;
        }
    }


    // ON BACK BTN CLICK
    public void back(View view) {
        if (currentStep > 0) {
            currentStep--;
        }
        stepView.done(false);
        stepView.go(currentStep, true);
        fragmentChange(R.anim.left_enter, R.anim.right_exit);
        if (currentStep == 0) {
            frameLayout.setVisibility(View.VISIBLE);
            rel_map.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
        }

    }

    // ON NEXT BTN CLICK
    public void next(View view) {
        if (fragmentId == 0) {
            StepOneFragment fragment = (StepOneFragment) fragmentManager.findFragmentByTag(UtilsProduct.Step_One);
            if (!fragment.checkEditText()) {
                //Toast.makeText(this, "Need to fill data!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (currentStep >= 2) {
            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }

        if (currentStep < stepView.getStepCount() - 1) {
            currentStep++;
            stepView.go(currentStep, true);
        } else {
            stepView.done(true);
            currentStep++;
        }
        btn.setVisibility(View.VISIBLE);
        fragmentChange(R.anim.right_enter, R.anim.left_exit);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gMap.setMyLocationEnabled(true);
        ImageButton img  = (ImageButton) findViewById(R.id.locateMe);


        gMap.setOnMyLocationButtonClickListener(this);
        gMap.setOnMyLocationClickListener(this);

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        myLocation = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        LatLng sydney = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        Circle radius = gMap.addCircle(new CircleOptions().center(sydney).radius(1000)
                .strokeColor(getResources().getColor(R.color.colorPrimaryDark))
                .fillColor(getResources().getColor(R.color.radiusIn))
        );
        gMap.getMaxZoomLevel();
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {

            if (permissions.length == 1 && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                gMap.setMyLocationEnabled(true);

            }

        }else{

        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        myLocation = location;
    }

    @Override
    public boolean onMyLocationButtonClick() {

        return false;
    }

    public void locateMe (View view){

    }
}
