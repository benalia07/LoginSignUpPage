package com.example.loginsignuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {
    Button Buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        String title = getIntent().getStringExtra("TITLE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String image = getIntent().getStringExtra("IMAGE");
        ArrayList<String> images = getIntent().getStringArrayListExtra("IMAGES");
        ImageSlider imageSlider = findViewById(R.id.imagesSlider);
        ImageView imageView = findViewById(R.id.back);
        Buy = findViewById(R.id.BuyBtn);
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           addNotification(title);
                Intent intent = new Intent(OrderPage.this, MainActivity1.class);


                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.title);
        textView.setText(title);

        TextView textView1 = findViewById(R.id.description);
        textView1.setText(description);
        addNotification(title);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(image, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    public void addNotification(String nom) {
        String Email = getIntent().getStringExtra("email");

        // Obtenez une référence à votre base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationRef = database.getReference("Notification");

        // Générez un identifiant unique pour la nouvelle notification
        String notificationId = notificationRef.push().getKey();

        // Créez un objet de données pour la nouvelle notification
        notification1 notification = new notification1();
        notification.setNom(nom);
        notification.setEmail(Email);

        // Créez un objet de données pour "Not1" avec ses sous-attributs
        Not1 not1 = new Not1();
        not1.setClient("Client");
        not1.setemail(Email);
        not1.setPrix(10.0);
        not1.setService("Service1");

        // Ajoutez "Not1" à la nouvelle notification
        notification.setNot1(not1);

        // Ajoutez la nouvelle notification à la base de données
        notificationRef.child(notificationId).setValue(notification);
    }
}




