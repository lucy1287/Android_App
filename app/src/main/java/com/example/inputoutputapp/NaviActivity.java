package com.example.inputoutputapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputoutputapp.databinding.ActivityMapBinding;
import com.example.inputoutputapp.databinding.ActivityNaviBinding;
import com.kakao.sdk.navi.NaviClient;
import com.kakao.sdk.navi.model.CoordType;
import com.kakao.sdk.navi.model.Location;
import com.kakao.sdk.navi.model.NaviOption;

public class NaviActivity extends AppCompatActivity {
    ActivityNaviBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNaviBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 카카오내비 앱으로 길 안내
        if (NaviClient.getInstance().isKakaoNaviInstalled(this)) {
            // 카카오내비 앱으로 길 안내 - WGS84
            startActivity(
                    NaviClient.getInstance().navigateIntent(
                            new Location("카카오 판교오피스", "127.108640", "37.402111"),
                            new NaviOption(CoordType.WGS84)
                    )
            );
        }
//         else {
//            // 카카오내비 설치 페이지로 이동
//            startActivity(
//                    Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse(SyncStateContract.Constants.WEB_NAVI_INSTALL)
//                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            )
//        }
    }
}
