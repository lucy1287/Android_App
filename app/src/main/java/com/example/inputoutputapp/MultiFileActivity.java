package com.example.inputoutputapp;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MultiFileActivity extends AppCompatActivity{
    private static final String TAG = "MultiFileActivity";

    ArrayList<Uri> uriList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();

    RecyclerView recyclerView;  // 이미지를 보여줄 리사이클러뷰
    MultiFileAdapter adapter;  // 리사이클러뷰에 적용시킬 어댑터


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_file);

        Button btnMultiFile = findViewById(R.id.btn_multi_file);
        btnMultiFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 1);
            }
        });

        recyclerView = findViewById(R.id.rv_multi_file);
    }

    // 파일탐색기에서 액티비티로 돌아온 후 실행
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {   // 어떤 파일도 선택하지 않은 경우
            Toast.makeText(getApplicationContext(), "파일을 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        } else {
            if (data.getClipData() == null) {     //파일 1개 선택
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri fileUri = data.getData();
                String fileName = getName(fileUri);
                uriList.add(fileUri);
                nameList.add(fileName);

                adapter = new MultiFileAdapter(uriList, nameList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
            } else {      // 파일을 여러장 선택한 경우
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                if (clipData.getItemCount() > 10) {
                    Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                    Log.e(TAG, "multiple choice");

                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri fileUri = clipData.getItemAt(i).getUri();
                        String fileName = getName(fileUri);

                        try {
                            uriList.add(fileUri);
                            nameList.add(fileName);

                        } catch (Exception e) {
                            Log.e(TAG, "File select error", e);
                        }
                    }

                    adapter = new MultiFileAdapter(uriList, nameList, getApplicationContext());
                    recyclerView.setAdapter(adapter);   // 리사이클러뷰에 어댑터 세팅
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
                }
            }
        }
    }

//    private String getPath(Uri uri)
//
//    {
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//
//        cursor.moveToFirst();
//
//        return cursor.getString(column_index);
//
//    }



// 파일명 찾기

    private String getName(Uri uri)

    {

        String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };

        Cursor cursor = managedQuery(uri, projection, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);

        cursor.moveToFirst();

        return cursor.getString(column_index);

    }
}

