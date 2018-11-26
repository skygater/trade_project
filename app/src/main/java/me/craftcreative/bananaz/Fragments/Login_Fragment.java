package me.craftcreative.bananaz.Fragments;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.craftcreative.bananaz.ProfileActivity;
import me.craftcreative.bananaz.R;
import me.craftcreative.bananaz.Tweaks.CustomToast;
import me.craftcreative.bananaz.Tweaks.UtilsLogIn;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    private static View view;

    private static EditText email, password;
    private static Button loginBtn;
    private static TextView forgotPassword, singUp;
    private static CheckBox show_hide_password;
    private static ImageButton close;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public Login_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews();
        setListeners();

        return view;
    }

    private void initViews(){
        fragmentManager = getActivity().getSupportFragmentManager();

        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        loginBtn = (Button) view.findViewById(R.id.login_btn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        singUp = (TextView) view.findViewById(R.id.create_accound);
        show_hide_password = (CheckBox) view.findViewById(R.id.login_password_visible);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);
        close = (ImageButton) view.findViewById(R.id.close_activity);

        //Shake Animation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.shake);
    }

    public void setListeners(){
        //THIS will use the OnCLick implementation
        // but we need to add context to this class
        loginBtn.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        singUp.setOnClickListener(this);
        close.setOnClickListener(this);

        // SET CHECK LISTENER
        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    show_hide_password.setText(R.string.login_checkbox_notvisible); // Change Text of Check box
                    password.setInputType(InputType.TYPE_CLASS_TEXT);// change type of Edit text Password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//Show Password
                }else {
                    show_hide_password.setText(R.string.login_checkbox_visible);//TextChange
                    password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);// change type of Edit text Password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());//Returns to password
                }
            }
        });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.close_activity:
                getActivity().finish();
                //Toast.makeText(getActivity(), "HOLA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_btn:
                //FOR NOWWW !!!!!!!
                //checkValidation();
                fragmentManager.beginTransaction()
                        .hide(this)
                        .commit();
                break;
            case R.id.forgot_password:
                ///Replace with other fragment! for changing the password!
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter,R.anim.left_exit)
                        .replace(R.id.frameContainer, new ForgotPassword_Fragment(),
                                UtilsLogIn.ForgotPassword_Fragment).commit();
                break;
            case R.id.create_accound:
                //Replace with other fragment! for creating acount!
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter,R.anim.left_exit)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),UtilsLogIn.SignUp_Fragment).commit();
                break;
        }

    }

    public void  checkValidation(){
        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();

        //Pattern init
        Pattern p = Pattern.compile(UtilsLogIn.regEx);
        Matcher m = p.matcher(getEmail);

        //Checker
        if(getEmail.equals("") || getEmail.length() == 0
                || getPassword.equals("") || getPassword.length()==0){
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().showToast(getActivity(),getString(R.string.login_error_email_pass),view);
        }else if(!m.find()){
            new CustomToast().showToast(getActivity(),getString(R.string.login_error_email_pattern),view);
        }else {
            Toast.makeText(getActivity(), "LOG IN SUCCESS", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction()
                    .hide(this)
                    .commit();
        }
    }

}
