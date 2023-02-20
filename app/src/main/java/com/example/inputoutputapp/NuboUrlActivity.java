package com.example.inputoutputapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityCallBinding;
import com.example.inputoutputapp.databinding.ActivityMessageBinding;
import com.example.inputoutputapp.databinding.ActivityNuboUrlBinding;

public class NuboUrlActivity extends AppCompatActivity {
    ActivityNuboUrlBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuboUrlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = binding.etUrl.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setComponent(new ComponentName("com.nubo.callconnector", "com.nubo.callconnector.CallConnectorActivity"));
                startActivity(intent);
            }
        });
    }
}
