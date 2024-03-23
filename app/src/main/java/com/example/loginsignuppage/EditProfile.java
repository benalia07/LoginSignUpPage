package com.example.loginsignuppage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfile extends Activity {
    private FirebaseAuth mAuth;
    private Button edit;
    private TextInputEditText emailLogin, passwordLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        passwordLogin = findViewById(R.id.passwords);
        emailLogin = findViewById(R.id.eMail);

       edit=findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            String email = emailLogin.getText().toString().trim();
            String password = passwordLogin.getText().toString().trim();
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ProfileFragment.class);

                       if(!email.isEmpty())
                       {
                           intent.putExtra("email", email);

                           changeEmail(email);
                       }
                if(!password.isEmpty())
                {
                    intent.putExtra("password",password);

                    changePassword(password);
                }
                startActivity(intent);

            }
        });




        // Autres initialisations et logique spécifique à EditProfile
    }
    private void changePassword(String newPassword) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Mot de passe mis à jour avec succès
                            // Faire quelque chose ici, comme afficher un message de succès
                        } else {
                            // Une erreur s'est produite lors de la mise à jour du mot de passe
                            // Afficher un message d'erreur ou gérer l'erreur en conséquence
                        }
                    });
        }
    }

    // Méthode pour changer l'e-mail de l'utilisateur
    private void changeEmail(String newEmail) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.updateEmail(newEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Adresse e-mail mise à jour avec succès
                            // Faire quelque chose ici, comme afficher un message de succès
                        } else {
                            // Une erreur s'est produite lors de la mise à jour de l'adresse e-mail
                            // Afficher un message d'erreur ou gérer l'erreur en conséquence
                        }
                    });
        }
    }
}