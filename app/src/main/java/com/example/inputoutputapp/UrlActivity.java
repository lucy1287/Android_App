package com.example.inputoutputapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityNuboUrlBinding;
import com.example.inputoutputapp.databinding.ActivityUrlBinding;

public class UrlActivity extends AppCompatActivity {
    ActivityUrlBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUrlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = binding.etUrl.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}