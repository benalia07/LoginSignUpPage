package com.example.loginsignuppage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsignuppage.R;
import com.example.loginsignuppage.Service2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Serviceajouter extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serviceajout);
        TextView Nom, Category, Description, Prix;
        Nom = findViewById(R.id.nom);
        Category = findViewById(R.id.category);
        Description = findViewById(R.id.description);
        Prix = findViewById(R.id.prix);
        Button terminer = findViewById(R.id.terminer);
        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = Nom.getText().toString();
                String d = Description.getText().toString();
                String c = Category.getText().toString();
                String p = Prix.getText().toString();
                String email = getIntent().getStringExtra("email");

                addService(n, c, d, p, email);
                Intent intent = new Intent(Serviceajouter.this, MainActivity1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addService(String Nom, String Category, String Description, String Prix, String Email) {
        // Obtenez une référence à votre base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference servicesRef = database.getReference("Services");

        // Générez un identifiant unique pour le nouveau service
        String serviceId = servicesRef.push().getKey();

        // Créez un objet de données pour le nouveau service
        Service2 service = new Service2(Nom, Category, Description, Prix,Email);
        Service2 service2 = new Service2(Nom, Category, Description, Prix,Email);

        // Ajoutez le nouveau service à la base de données
        servicesRef.child(serviceId).setValue(service2);
    }
}
