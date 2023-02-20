package com.example.inputoutputapp;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    public void onCreate() {
        super.onCreate();
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "ac4a3d98cf8e116392d79863f3f9c800");
    }
}
