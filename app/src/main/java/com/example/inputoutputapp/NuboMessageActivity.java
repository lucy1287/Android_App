package com.example.inputoutputapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityMessageBinding;

public class NuboMessageActivity extends AppCompatActivity {
    ActivityMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String message = binding.etMessage.getText().toString();

                Uri uri = Uri.parse("sms: " + phoneNumber);
                Intent messageIntent = new Intent(Intent.ACTION_SENDTO, uri);
                messageIntent.setComponent(new ComponentName("com.nubo.callconnector", "com.nubo.callconnector.CallConnectorActivity"));;
                messageIntent.putExtra("subject", "MMS SEND");
                messageIntent.putExtra("sms_body", message);
                startActivity(messageIntent);

            }
        });
    }
}
