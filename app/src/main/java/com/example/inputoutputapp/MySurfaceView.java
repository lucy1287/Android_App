package com.example.inputoutputapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    Context mContext;
    SurfaceHolder mHolder;
    RenderingThread mRThread;

    public MySurfaceView(Context context) {
        super(context);
        mContext = context;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mRThread = new MySurfaceView.RenderingThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Surface가 만들어질 때 호출됨
        mRThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        // Surface가 변경될 때 호출됨
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Surface가 종료될 때 호출됨
        try {
            mRThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class RenderingThread extends Thread {
        Bitmap img_android;

        public RenderingThread() {
            Log.d("RenderingThread", "RenderingThread()");
            img_android = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background);
        }

        public void run() {
            Log.d("RenderingThread", "run()");
            Canvas canvas = null;
            while (true) {
                //canvas lock을 통해서 해당 캔버스에 draw가 들어와도 그리지 않는다.
                canvas = mHolder.lockCanvas();
                try {
                    synchronized (mHolder) {
                    //    canvas.drawBitmap(img_android, 0, 0, null);
                    }
                } finally {
                    if (canvas == null) return;
                    //여기서 lock된 draw들을 그린다.
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    } // RenderingThread
}
