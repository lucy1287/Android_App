package com.example.inputoutputapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityNextBinding;

public class NextActivity extends AppCompatActivity {

    ActivityNextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String outputText = intent.getExtras().getString("inputText");
        binding.tvOutput.setText(outputText);
    }
}
