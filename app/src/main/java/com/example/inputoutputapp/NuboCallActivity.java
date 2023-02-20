package com.example.inputoutputapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityCallBinding;

public class NuboCallActivity extends AppCompatActivity {

    ActivityCallBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                Uri uri = Uri.parse("tel: " + phoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                callIntent.setComponent(new ComponentName("com.nubo.callconnector", "com.nubo.callconnector.CallConnectorActivity"));
                startActivity(callIntent);
            }
        });
    }
}
