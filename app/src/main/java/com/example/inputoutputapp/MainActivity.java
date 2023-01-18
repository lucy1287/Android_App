package com.example.inputoutputapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inputoutputapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = getInputText();
                makeIntent(inputText);
            }
        });

        binding.btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = getInputText();
                makePopup(inputText);
            }
        });

        binding.btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = getInputText();
                makeDialog(inputText);
            }
        });
    }

    public String getInputText(){
        String inputText = binding.etInput.getText().toString();
        return inputText;
    }

    public void makeIntent(String inputText){
        Intent intent = new Intent(getApplicationContext(), NextActivity.class);
        intent.putExtra("inputText", inputText);
        startActivity(intent);
    }

    public void makePopup(String inputText){
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("inputText", inputText);
        startActivity(intent);
    }

    public void makeDialog(String inputText){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("텍스트").setMessage(inputText);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog 제거
                dialog.dismiss();
            }
        });

        // 취소 버튼
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog 제거
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}