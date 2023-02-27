package com.example.inputoutputapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.inputoutputapp.databinding.ActivityMapKakaoBinding;
import com.naver.maps.map.NaverMap;

import net.daum.mf.map.api.MapView;

public class MapKakaoActivity extends AppCompatActivity{
    ActivityMapKakaoBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapKakaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
