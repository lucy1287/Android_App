package com.example.inputoutputapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.inputoutputapp.databinding.ActivityContactBinding;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends AppCompatActivity {

    ActivityContactBinding binding;

    private String TAG = ContactActivity.class.getSimpleName();
    private Context context = ContactActivity.this;
    final int PERMISSION_REQUEST_CODE = 1;

    public static String[] requiredPermissionList = new String[]{  //필요한 권한들
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };

    ArrayList<String> neededPermissionList = new ArrayList<>(); //권한 요청이 필요한 리스트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRequestPermissionsCheck()) {
                    goNext(true);
                }

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.Contacts.CONTENT_URI);
               // intent.setType("vnd.android.cursor.item/phone_v2");
                startActivityForResult(intent, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            String id= Uri.parse(data.getDataString()).getLastPathSegment();
            binding.tvResult.setText(id);

//            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER },
//                    null, null, null);
//            if(cursor.moveToFirst()){
//                do{
//                    String name = cursor.getString(0);
//                    String phone=cursor.getString(1);
//                    Log.d("결과", name + phone);
//                } while(cursor.moveToNext());
//            }

            Cursor cursor = getApplicationContext().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {@SuppressLint("Range\n" +
                            "                    ") String mobileNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    Log.d("mobileNumber :: ", mobileNumber);
                    Log.d("name :: ", name);
                } while (cursor.moveToNext());
            }
            cursor.close();


        }
    }

    public void checkPermission() {

        //안드로이드 OS 버전이 23 이상일 경우 - 권한 허용 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String thisPermission : requiredPermissionList) {
                //해당 권한이 있는지 확인
                if (ContextCompat.checkSelfPermission(context, thisPermission) != PackageManager.PERMISSION_GRANTED) {
                    neededPermissionList.add(thisPermission);   //권한허용이 필요한 경우 neededPermissionList 리스트에 추가해준다
                }
            }

            if(neededPermissionList.size() > 0) {
//                initView();  //권한허용이 필요한 경우 initView() 에서 권한 요청 팝업을 띄워준다
            } else {
                goNext(true);
            }

        } else {  //안드로이드 OS 버전이 23 이상이 아닐 경우 - 권한 허용 불필요
            goNext(true);
        }
    }



    /* 권한 요청 코드 -> ~ 권한 허용하시겠습니까? 팝업 표시 */
    private boolean onRequestPermissionsCheck() {
        if (!neededPermissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, neededPermissionList.toArray(new String[neededPermissionList.size()]), PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }


    /* 권한 요청에 대한 팝업 결과(허용/거부) 처리 */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> deniedPermission = new ArrayList<String>();  //권한 요청이 거부된 리스트

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        Log.d(TAG, "***** onRequestPermissionsResult() - result : " + i);
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            //권한 거부 - 권한허용이 필요한 거부된 경우 deniedPermission 리스트에 추가해준다
                            deniedPermission.add(requiredPermissionList[i]);
                        }
                    }

                    if (deniedPermission.size() < 1) {
                        goNext(true);
                    } else {
                        goNext(false);
                    }
                }
                return;
        }
    }


    /* 권한 요청에 따른 결과 Toast 표시 */
    private void goNext(boolean successFlag){
        Log.d(TAG, "***** goNext() - successFlag : "+successFlag);
        if(successFlag) {
            Toast.makeText(context, "모든 권한을 허용했네요!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "모든 권한을 허용해야합니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
