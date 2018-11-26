package me.craftcreative.bananaz.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import me.craftcreative.bananaz.Adapters.AddImgList;
import me.craftcreative.bananaz.AddProductActivity;
import me.craftcreative.bananaz.ProfileActivity;
import me.craftcreative.bananaz.R;

public class StepOneFragment extends Fragment implements View.OnClickListener {

    //INIT VIEW
    private static View view;

    //ALL ELEMENTS for Fragment
    private EditText nameProduct, descriptionProduct, tagProduct;
    private ImageButton btn_add;
    private Button btn_delete_all;
    private View line;

    //LIST OF SELECTED IMG
    List<Image> images;
    private static final int RC_CAMERA = 3000;

    //RECYCLER VIEW LIST ELEMENTS
    private RecyclerView listSelected;
    private AddImgList listAdapter;
    //IMG URLS
    List<String> imgUrls;
    int picLimit = 3;


    /*INIT Fragment*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_step_one,container,false);
        initViews();
        setListeners();
        return view;
    }

    /*INIT Fragment ELEMENTS*/

    private void initViews(){
        //ELEMENTS findviewbyID();
        nameProduct = (EditText) view.findViewById(R.id.add_product_name);//EDIT TEXT TITLE
        descriptionProduct = (EditText) view.findViewById(R.id.add_product_description);//EDIT TEXT DESCRIPTION
//      tagProduct = (EditText) view.findViewById(R.id.add_product_tag);
        btn_add = (ImageButton) view.findViewById(R.id.btn_add_pics);//BTN add PHOTO
        btn_delete_all = (Button) view.findViewById(R.id.btn_delete_all_img);//BTN delete all PHOTO
        line = (View) view.findViewById(R.id.view_add_picture);//SEPERATION LINE

        //SETING THE ADDPICTURE BTN CENTER
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams)btn_add.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        btn_add.setLayoutParams( layoutParams);

        //VISIBILITY btn DELETE ALL - Seperator LINE
        line.setVisibility(View.GONE);
        btn_delete_all.setVisibility(View.GONE);
        //INIT LIST img URLS
        imgUrls = new ArrayList<>();
        //SETING RECYCLER LIST AND MENAGER
        listSelected = (RecyclerView) view.findViewById(R.id.add_product_img_list);
        LinearLayoutManager mLinearMen = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false);
        listSelected .setLayoutManager(mLinearMen);
    }

    /* INIT Fragment BTNs */
    public void setListeners(){
        btn_add.setOnClickListener(this);
        btn_delete_all.setOnClickListener(this);
    }

    /* Getting all IMGs and fill the LIST<IMGs> */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            images = (ArrayList<Image>) ImagePicker.getImages(data);
            //FUNC for adding IMGs in RECYCLER VIEW
            printImages(images);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* INIT OnCLick ACTION */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //--> BTN add PICTURES
            case R.id.btn_add_pics:

                //CHECK THE current img count;
                if(imgUrls.size() == 3){
                    Toast.makeText(view.getContext(), getString(R.string.step_one_info_limit_pic), Toast.LENGTH_SHORT).show();
                    return;
                }
                // INIT THE IMAGE PICKER lib
                ImagePicker imagePicker = ImagePicker.create(this)
                        .language("en") // Set image picker language
                        .theme(R.style.ImagePickerTheme) //Style of IMG PICTURE lib
                        .returnMode( ReturnMode.NONE) // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
                        .folderMode(false) // set folder mode (false by default)
                        .toolbarArrowColor(Color.RED) // set toolbar arrow up color
                        .toolbarFolderTitle("Folder") // folder selection title
                        .toolbarImageTitle("Tap to select") // image selection title
                        .multi();
                //SETING THE LIMIT to 3
                if (imgUrls.size() == 0){
                    imagePicker.limit(picLimit );
                }else{
                    imagePicker.limit(picLimit -imgUrls.size());
                }
                // INIT THE IMAGE PICKER lib
                imagePicker
                        .showCamera(true)
                        .imageDirectory("Camera")
                        .start();
                break;

            //--> BTN delete all PICTURES
            case R.id.btn_delete_all_img:

                //Setting ADDPICTURE position left
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) btn_add.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                btn_add.setLayoutParams( layoutParams);
                //Checks if there are IMGs
                if(imgUrls.size() == 0){
                    Toast.makeText(view.getContext(), getString(R.string.step_one_info_noPic), Toast.LENGTH_SHORT).show();
                    return;
                }
                // REFRESH the ADAPTER and RECYCLER VIEW
                imgUrls = new ArrayList<>();
                listAdapter = new AddImgList(view.getContext(),imgUrls);
                listSelected.setAdapter(listAdapter);
                //SETTING VISIBILITY
                btn_delete_all.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                break;
        }
    }

    //FUNC SETTING IMGs
    private void printImages(List<Image> images) {
        //If NO IMGs chosen
        if (images == null) return;
        //Go trough IMGs LIST
        for (int i = 0 ; i < images.size(); i++) {
            //ADDing IMG URLs to ImgUrls LIST
            imgUrls.add(images.get(i).getPath());
        }
        //SETTING VISIBILITY
        line.setVisibility(View.VISIBLE);
        btn_delete_all.setVisibility(View.VISIBLE);
        //Setting ADDPICTURE position left
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams)btn_add.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        btn_add.setLayoutParams( layoutParams);
        //Setting Adapter and
        listAdapter = new AddImgList(view.getContext(),imgUrls);
        listSelected.setAdapter(listAdapter);
    }

    /* CHECK THE EDIT TEXT */

    public boolean checkEditText(){
        String title = nameProduct.getText().toString();
        String description = descriptionProduct.getText().toString();

        if(title.equalsIgnoreCase("") || title.length() ==0 ||
                description.equalsIgnoreCase("") || description.length() == 0){
            nameProduct.setHint(getString(R.string.step_one_info_title_er));
            nameProduct.setHintTextColor(getResources().getColor(R.color.lipsRed));
            descriptionProduct.setHintTextColor(getResources().getColor(R.color.lipsRed));
            descriptionProduct.setHint(getString(R.string.step_one_info_description_er));
            return false;
        }else{
            return true;
        }
    }

    //PERMISSION for CAMERA;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == RC_CAMERA) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
