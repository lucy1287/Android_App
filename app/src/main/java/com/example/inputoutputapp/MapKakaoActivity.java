package com.example.inputoutputapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityMapKakaoBinding;

import net.daum.mf.map.api.MapView;

public class MapKakaoActivity extends AppCompatActivity{
    ActivityMapKakaoBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMapKakaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View mapView = binding.mapView;

    }
}
