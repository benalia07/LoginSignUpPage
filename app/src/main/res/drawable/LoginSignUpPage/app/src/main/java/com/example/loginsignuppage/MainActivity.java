package com.example.loginsignuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputEditText emailLogin , passwordLogin , emailSignup , passwordSignup , confirmPassword;
    TextView signUp , login , incorrectData;
    Button loginBtn , signupBtn;
    LinearLayout signupLayout , loginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        signupLayout = findViewById(R.id.signUpLayout);
        loginLayout = findViewById(R.id.loginLayout);
        loginBtn = findViewById(R.id.loginBtn);
        emailLogin = findViewById(R.id.eMail);
        passwordLogin = findViewById(R.id.passwords);
        emailSignup = findViewById(R.id.signupEMail);
        passwordSignup = findViewById(R.id.signUppasswords);
        confirmPassword = findViewById(R.id.confirmpasswords);
        incorrectData = findViewById(R.id.incorrectMsg);
        signupBtn = findViewById(R.id.signupButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                signUp.setBackground(getDrawable(R.drawable.switch_tricks));
                signUp.setTextColor(getColor(R.color.textColor));
                login.setBackground(null);
                login.setTextColor(getColor(R.color.blueColor));
                signupLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                incorrectData.setVisibility(View.GONE);
                signupBtn.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.GONE);
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                login.setBackground(getDrawable(R.drawable.switch_tricks));
                login.setTextColor(getColor(R.color.textColor));
                signUp.setBackground(null);
                signUp.setTextColor(getColor(R.color.blueColor));
                loginLayout.setVisibility(View.VISIBLE);
                signupLayout.setVisibility(View.GONE);
                signupBtn.setVisibility(View.GONE);
                loginBtn.setVisibility(View.VISIBLE);
            }

        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.requireNonNull(emailLogin.getText()).toString().equals("user") && Objects.requireNonNull(passwordLogin.getText()).toString().equals("1234")){
                    startActivity(new Intent(MainActivity.this , MainActivity1.class ));
                    incorrectData.setVisibility(View.GONE);
                }
                else{
                    incorrectData.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}