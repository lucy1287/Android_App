package com.example.inputoutputapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityMapBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;


public class MapActivity extends AppCompatActivity {
    ActivityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                try {

                    String location = "경기도 수원시 장안구";

                    String addr = "https://dapi.kakao.com/v2/local/search/address.json";

                    String apiKey = "KakaoAK { APIKEY }";

                    location = URLEncoder.encode(location, "UTF-8");

                    String query = "query=" + location;

                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(addr);
                    stringBuffer.append("?");
                    stringBuffer.append(query);

                    System.out.println("stringBuffer.toString() "+ stringBuffer.toString());

                    URL url = new URL(stringBuffer.toString());

                    URLConnection conn = url.openConnection();

                    conn.setRequestProperty("Authorization", apiKey);

                    BufferedReader rd = null;

                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                    StringBuffer docJson = new StringBuffer();

                    String line;

                    while((line=rd.readLine())!=null){
                        docJson.append(line);
                    }

                    if(0<docJson.toString().length()){
                        System.out.println("docJson    :"+docJson.toString());

                    }

                    rd.close();

                    JSONObject jsonObject = new JSONObject(docJson.toString());

                    JSONArray jsonArray= (JSONArray) jsonObject.get("documents");

                    JSONObject tempObj = (JSONObject) jsonArray.get(0);

                    System.out.println("latitude : " + tempObj.getDouble("y"));
                    System.out.println("longitude : " + tempObj.getDouble("x"));

                }catch(Exception e) {
                    e.printStackTrace();
                }

                    String latitude = binding.etLatitude.getText().toString();
                    String longitude = binding.etLongitude.getText().toString();

                    String url = "kakaomap://look?p=" + latitude + "," + longitude;
                    Log.d("latitude", binding.etLatitude.getText().toString());
                    Intent intentMap = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    // intentMap.addCategory(Intent.CATEGORY_BROWSABLE);
                    //  List<ResolveInfo> list = getPackageManager().queryIntentActivities(intentMap, PackageManager.MATCH_DEFAULT_ONLY);
                    // if(list == null || list.isEmpty())
                    //     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=net.daum.android.map")));
                    // else
                    startActivity(intentMap);

            }
        });
    }
}