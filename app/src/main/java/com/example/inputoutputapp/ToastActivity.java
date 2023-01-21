package com.example.inputoutputapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityToastBinding;

public class ToastActivity extends AppCompatActivity {
    ActivityToastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnOk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String toastMessage = binding.etToastMessage.getText().toString();

                Toast myToast = Toast.makeText(getApplicationContext(),toastMessage, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }
}
