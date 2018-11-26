package me.craftcreative.bananaz;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

public class AddProductActivity extends AppCompatActivity implements OnMapReadyCallback {

    //STEPS
    StepView stepView;
    private int currentStep = 0;
    //Fragment
    private static FragmentManager fragmentManager;
    FrameLayout frameLayout;
    RelativeLayout rel_map;
    //View Init
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        //InitVIew
        btn = (Button) findViewById(R.id.btn_one_back);

        //INIT STEPS
        stepView = (StepView) findViewById(R.id.stepview);
        List<String>name = new ArrayList<>();
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
                .replace(R.id.frame_product,new StepOneFragment(), UtilsProduct.Step_One)
                .commit();

        // MAP test
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public int fragmentId =0;
    //CHANGE FRAGMENT 1-3
    public void fragmentChange(int anim1, int anim2){

        switch (currentStep){
            case 0:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1,anim2)
                        .replace(R.id.frame_product, new StepOneFragment(),
                                UtilsProduct.Step_One).commit();
                fragmentId = 0;
                break;
            case 1:
                frameLayout.setVisibility(View.GONE);
                rel_map.setVisibility(View.VISIBLE);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1,anim2)
                        .replace(R.id.frame_product, new StepTwoFragment(),
                                UtilsProduct.Step_Two).commit();
                fragmentId = 1;
                break;
            case 2:
                frameLayout.setVisibility(View.VISIBLE);
                rel_map.setVisibility(View.GONE);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(anim1,anim2)
                        .replace(R.id.frame_product, new StepThreeFragment(),
                                UtilsProduct.Step_Three).commit();
                fragmentId = 2;
                break;
        }
    }


    // ON BACK BTN CLICK
    public void back (View view){
        if (currentStep > 0) {
            currentStep--;
        }
        stepView.done(false);
        stepView.go(currentStep, true);
        fragmentChange(R.anim.left_enter, R.anim.right_exit);
        if(currentStep == 0){
            frameLayout.setVisibility(View.VISIBLE);
            rel_map.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
        }

    }
    // ON NEXT BTN CLICK
    public void next (View view){
        if (fragmentId == 0) {
            StepOneFragment fragment = (StepOneFragment) fragmentManager.findFragmentByTag(UtilsProduct.Step_One);
            if (!fragment.checkEditText()) {
                //Toast.makeText(this, "Need to fill data!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (currentStep>=2){
            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
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
        fragmentChange(R.anim.right_enter,R.anim.left_exit);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker( new MarkerOptions().position(sydney))
                .setTitle("Marker in Sydney");
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


//    private static final int RC_CAMERA = 3000;
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            if (requestCode == RC_CAMERA) {
//                if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //captureImage();
//                }
//            }
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//    }
}
