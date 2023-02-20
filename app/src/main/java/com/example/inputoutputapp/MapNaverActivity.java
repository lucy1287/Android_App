package com.example.inputoutputapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.inputoutputapp.databinding.ActivityMapNaverBinding;

public class MapNaverActivity extends AppCompatActivity {
    ActivityMapNaverBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMapNaverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View mapView = binding.mapView;

    }
}
