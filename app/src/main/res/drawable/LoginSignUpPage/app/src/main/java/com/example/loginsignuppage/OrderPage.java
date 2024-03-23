package com.example.loginsignuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        //slideModels.add(new SlideModel(R.drawable.desert, ScaleTypes.FIT));
        //slideModels.add(new SlideModel(R.drawable.koala, ScaleTypes.FIT));
        //slideModels.add(new SlideModel(R.drawable.hydrangeas, ScaleTypes.FIT));
        //slideModels.add(new SlideModel(R.drawable.lighthouse, ScaleTypes.FIT));
        //slideModels.add(new SlideModel(R.drawable.penguins, ScaleTypes.FIT));

        //imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}