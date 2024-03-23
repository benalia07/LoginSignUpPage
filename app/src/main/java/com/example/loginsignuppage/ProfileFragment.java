package com.example.loginsignuppage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private TextView emailTextView;
   private Button Edit;
   ImageView logout;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        emailTextView = view.findViewById(R.id.textView2);
        String Email = getActivity().getIntent().getStringExtra("email");
        //String PassWord = getActivity().getIntent().getStringExtra("password");
      logout=view.findViewById(R.id.logout);
      logout.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(getActivity(), MainActivity.class);


                      startActivity(intent);
                      getActivity().finish();
                  }
              }
      );
        emailTextView.setText(Email);
        Edit=view.findViewById(R.id.edit);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }
}