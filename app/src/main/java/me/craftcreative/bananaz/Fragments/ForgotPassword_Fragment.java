package me.craftcreative.bananaz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.craftcreative.bananaz.ProfileActivity;
import me.craftcreative.bananaz.R;
import me.craftcreative.bananaz.Tweaks.CustomToast;
import me.craftcreative.bananaz.Tweaks.UtilsLogIn;

public class ForgotPassword_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static EditText email;
    private static TextView submit,back;
    private static ImageButton close;


    public ForgotPassword_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgot_pass,container,false);
        initViews();
        setListeners();
        return view;
    }

    public void initViews (){
        email = (EditText) view.findViewById(R.id.forgot_email);
        submit = (TextView) view.findViewById(R.id.forgot_submit);
        back = (TextView) view.findViewById(R.id.forgot_back);
        close =(ImageButton) view.findViewById(R.id.close_activity_forgot);
    }

    public void setListeners(){
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.close_activity_forgot:
                getActivity().finish();
                break;
            case R.id.forgot_back:
                new ProfileActivity().replaceLoginFragment();
                break;
            case R.id.forgot_submit:
                submitButtonTask();
                break;
        }

    }

    private void submitButtonTask(){
        String getEmail = email.getText().toString();

        //Pattern
        Pattern p = Pattern.compile(UtilsLogIn.regEx);
        Matcher m = p.matcher(getEmail);

        if (getEmail.equals("") || getEmail.length()==0){
            new CustomToast().showToast(getActivity(),getString(R.string.forgot_error_fields),view);
        }else if(!m.find()){
            new CustomToast().showToast(getActivity(),getString(R.string.forgot_error_email),view);
        }else {
            //Toast.makeText(getActivity(), "You Will get an e-mail!", Toast.LENGTH_SHORT).show();
            new CustomToast().showToast(getActivity(),getString(R.string.succes_forgot),view);
            new ProfileActivity().replaceLoginFragment();
        }
    }
}
