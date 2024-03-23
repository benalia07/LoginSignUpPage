package com.example.loginsignuppage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText emailLogin, passwordLogin, emailSignup, passwordSignup, confirmPassword;
    private TextView signUp, login, incorrectData;
    private Button loginBtn, signupBtn;
    private LinearLayout signupLayout, loginLayout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer l'adresse e-mail de l'intent

        mAuth = FirebaseAuth.getInstance();

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
        String email = getIntent().getStringExtra("email");

        // Créer une instance de OrdersFragment


        // Remplacer le contenu du fragment_container par OrdersFragment

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Connexion réussie, mettre à jour l'interface utilisateur avec les informations de l'utilisateur connecté
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    sendEmailToOrdersFragment();
                                    String email = getIntent().getStringExtra("email");
                                    String password = getIntent().getStringExtra("password");

                                    // Créer une instance de OrdersFragment
                                    OrdersFragment fragment = new OrdersFragment();
                                    ProfileFragment fr=new ProfileFragment();
                                    // Passer l'adresse e-mail à OrdersFragment
                                    Bundle bundle = new Bundle();
                                    bundle.putString("email", email);
                                    bundle.putString("password",password);
                                    fragment.setArguments(bundle);
                                    fr.setArguments(bundle);

                                    // Remplacer le contenu du fragment_container par OrdersFragment

                                    sendStringToClassB(emailLogin.getText().toString(),passwordLogin.getText().toString());
                                    incorrectData.setVisibility(View.GONE);
                                } else {
                                    // En cas d'échec de la connexion, afficher un message à l'utilisateur.
                                    incorrectData.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailSignup.getText().toString().trim();
                String password = passwordSignup.getText().toString().trim();
                String confirm_password = confirmPassword.getText().toString().trim();

                if (password.equals(confirm_password)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Inscription réussie, mettre à jour l'interface utilisateur avec les informations de l'utilisateur inscrit
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Créer une instance de OrdersFragment
                                        String email = getIntent().getStringExtra("email");
                                        String password = getIntent().getStringExtra("password");

                                        // Créer une instance de OrdersFragment
                                        OrdersFragment fragment = new OrdersFragment();
                                        ProfileFragment fr=new ProfileFragment();
                                        // Passer l'adresse e-mail à OrdersFragment
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", email);
                                        bundle.putString("password",password);
                                        fragment.setArguments(bundle);
                                        fr.setArguments(bundle);
                                        sendEmailToOrdersFragment();
                                        sendStringToClassB(emailLogin.getText().toString(),passwordLogin.getText().toString());
                                        incorrectData.setVisibility(View.GONE);
                                    } else {
                                        // En cas d'échec de l'inscription, afficher un message à l'utilisateur.
                                        incorrectData.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                } else {
                    // Afficher un message d'erreur si les mots de passe ne correspondent pas
                    incorrectData.setVisibility(View.VISIBLE);
                    incorrectData.setText("Passwords do not match");
                }
            }
        });
    }

    private void sendEmailToOrdersFragment() {
        Intent intent = new Intent(MainActivity.this, MainActivity1.class);
        intent.putExtra("email", emailLogin.getText().toString());
        intent.putExtra("password", passwordLogin.getText().toString());

        startActivity(intent);
        finish();
    }
    private void sendStringToClassB(String email,String password) {
        Intent intent = new Intent(MainActivity.this, MainActivity1.class);
        intent.putExtra("email", email);
        intent.putExtra("password",password);

        startActivity(intent);
        finish();
    }


    public String getemail() {
        String email = emailLogin.getText().toString();
        return email;
    }
}
