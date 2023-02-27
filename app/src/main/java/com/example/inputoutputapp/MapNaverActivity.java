package com.example.inputoutputapp;

import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.inputoutputapp.databinding.ActivityMapKakaoBinding;
import com.example.inputoutputapp.databinding.ActivityMapNaverBinding;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;

public class
MapNaverActivity extends AppCompatActivity {
    ActivityMapNaverBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapNaverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
                                                                                                      