package com.example.vote_app.Registor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vote_app.R;
import com.example.vote_app.login.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

public class Registor extends Activity {

    private TextInputLayout Tuser_name;
    private TextInputLayout Tpassword;
    private TextInputLayout Tpassword1;
    private Button registor_button;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registor);

        back_button = (Button)findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
