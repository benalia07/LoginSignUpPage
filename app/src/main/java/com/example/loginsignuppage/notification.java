package com.example.loginsignuppage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class notification extends AppCompatActivity {
    private TextView textViewNotification;
    private TextView textViewPrix;
    private TextView textViewClient;
    private TextView textViewService;
    private Button accepterButton;
    private String client;
    private String service;
    private int prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        textViewNotification = findViewById(R.id.textView16);
        textViewPrix = findViewById(R.id.textView20);
        textViewClient = findViewById(R.id.textView22);
        textViewService = findViewById(R.id.textView18);
        accepterButton = findViewById(R.id.button2);

        // Récupérer les données de l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            client = extras.getString("CLIENT");
            service = extras.getString("SERVICE");
            prix = extras.getInt("PRICE");
        }

        textViewClient.setText(client);
        textViewPrix.setText(String.valueOf(prix) + " $");
        textViewService.setText(service);

        accepterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmail(client, service, prix);
                Intent intent = new Intent(notification.this, OrdersFragment.class);


                startActivity(intent);
            }
        });
    }

    private void sendEmail(String client, String service, int prix) {
        String recipientEmail = client+"@gmail.com"; // Remplacez par l'adresse email du destinataire
        String subject = "Acceptation de la commande";
        String content = "Bonjour " + client + ",\n\n"
                + "Nous avons le plaisir de vous informer que votre commande pour le service "
                + service + " a été acceptée. Le montant total est de " + prix + " $.\n\n"
                + "Merci de votre confiance !\n\n"
                + "Cordialement,\n"
                + "Votre équipe de service";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + recipientEmail));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);

        try {
            startActivity(Intent.createChooser(intent, "Choisir une application de messagerie"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(notification.this, "Aucune application de messagerie installée.", Toast.LENGTH_SHORT).show();
        }
    }
}
