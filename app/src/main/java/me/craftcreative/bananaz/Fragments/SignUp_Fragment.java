package me.craftcreative.bananaz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SignUp_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static EditText name,lastname,email,password,password2;
    private static TextView login;
    private static Button singUpButton;
    private static CheckBox terms_conditions;

    private static ImageButton close;

    public SignUp_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_singin,container,false);
        initViews();
        setListeners();
        return view;

    }

    public void initViews(){
        name = (EditText) view.findViewById(R.id.sing_name);
        lastname = (EditText) view.findViewById(R.id.sing_lastname);
        email = (EditText) view.findViewById(R.id.sing_email);
        password = (EditText) view.findViewById(R.id.sing_password);
        password2 = (EditText) view.findViewById(R.id.sing_password_confirm);
        close = (ImageButton) view.findViewById(R.id.close_activity_singup);
        singUpButton = (Button) view.findViewById(R.id.sing_btn);
        login = (TextView) view.findViewById(R.id.sing_login);
        terms_conditions = (CheckBox) view.findViewById(R.id.sing_checkbox_terms);
    }

    public void setListeners(){
        singUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_activity_singup:
                getActivity().finish();
                break;
            case R.id.sing_btn:
                checkValidation();
                break;
            case R.id.sing_login:
                new ProfileActivity().replaceLoginFragment();
                break;
        }

    }

    private void checkValidation(){
        String getName = name.getText().toString();
        String getLastname = lastname.getText().toString();
        String getEmail = email.getText().toString();
        String getPass = password.getText().toString();
        String getPassCom = password2.getText().toString();

        //Patterns INIT
        Pattern p = Pattern.compile(UtilsLogIn.regEx);
        Matcher m = p.matcher(getEmail);

        //Checker
        if (getName.equals("") || getName.length() == 0
                || getLastname.equals("") || getLastname.length() == 0
                || getEmail.equals("") || getEmail.length() ==0
                || getPass.equals("") || getPass.length() == 0
                || getPassCom.equals("") || getPassCom.length()==0){

           new CustomToast().showToast(getActivity(),getString(R.string.singup_error_filds),view);
        }else if (!m.find()){
            new CustomToast().showToast(getActivity(),getString(R.string.singup_error_email),view);
        }else if(!getPassCom.equals(getPass)){
            new CustomToast().showToast(getActivity(),getString(R.string.singup_error_password),view);
        }else if (!terms_conditions.isChecked()){
            new CustomToast().showToast(getActivity(),getString(R.string.singup_error_terms),view);
        }else{
           // Toast.makeText(getActivity(),"Success Sing In",Toast.LENGTH_SHORT).show();
            new CustomToast().showToast(getActivity(),getString(R.string.succes_singup),view);
            new ProfileActivity().replaceLoginFragment();
        }


    }
}
