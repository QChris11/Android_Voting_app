package com.example.vote_app.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vote_app.Main2Activity;
import com.example.vote_app.R;
import com.example.vote_app.Registor.Registor;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    private Button login_button;
    private Button registor_button;
    private TextInputLayout Tuser_name;
    private TextInputLayout Tpassword;
    private EditText user_name_text;
    private EditText password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tuser_name = findViewById(R.id.username_text_input);
        Tpassword = findViewById(R.id.password_text_input);
        login_button = (Button)findViewById(R.id.login);
        registor_button = (Button)findViewById(R.id.register);
        user_name_text = Tuser_name.getEditText();
        password_text = Tpassword.getEditText();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tuser_name.setErrorEnabled(false);
                Tpassword.setErrorEnabled(false);

                login_check();
            }
        });

        registor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registor.class);
                startActivity(intent);
            }
        });

        user_name_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Tuser_name.getError()!=null){
                    Tuser_name.setError(null);
                }
            }
        });

        password_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Tpassword.getError()!=null){
                    Tpassword.setError(null);
                }
            }
        });
    }

    /*
     * 用于判断登录账号密码
     */
    public void login_check(){

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
//        final String user_name;
//        final String password;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            user_name = Objects.requireNonNull(user_name_text.getText()).toString();
//            password = Objects.requireNonNull(password_text.getText()).toString();
//            if(validateAccount(user_name) && validatePassword(password)){
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        }
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     */
    private boolean validateAccount(String account){
        if(isEmpty(account)){
            showError(Tuser_name,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     */
    private boolean validatePassword(String password) {
        if (isEmpty(password)) {
            showError(Tpassword,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(Tpassword,"密码长度为6-18位");
            return false;
        }

        return true;
    }

    public void hideKeyBord(View v) {
        View view=getCurrentFocus();
        if(null!=view){
            InputMethodManager manager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(),0);
            user_name_text.clearFocus();
            password_text.clearFocus();
        }
    }
}
