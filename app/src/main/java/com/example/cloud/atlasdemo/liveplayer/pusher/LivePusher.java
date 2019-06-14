package com.example.cloud.atlasdemo.liveplayer.pusher;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.cloud.atlasdemo.liveplayer.nativeinterf.PushNative;
import com.example.cloud.atlasdemo.liveplayer.params.AudioParam;
import com.example.cloud.atlasdemo.liveplayer.params.VideoParam;

/**
 * Created by cloud on 2019/6/12.
 */

public class LivePusher implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private PushNative pushNative;
    private VideoPusher videoPusher;
    private AudioPusher audioPusher;
    private String TAG = "Push";

    public LivePusher(SurfaceHolder holder) {
        this.holder = holder;
        holder.addCallback(this);
        prepare();
    }

    private void prepare() {
        pushNative = new PushNative();
//实例化视频推流器
        VideoParam videoParam = new VideoParam(480, 320, Camera.CameraInfo.CAMERA_FACING_BACK);
        videoPusher = new VideoPusher(holder,videoParam,pushNative);

        //实例化音频推流器
        AudioParam audioParam = new AudioParam();
        audioPusher = new AudioPusher(audioParam,pushNative);
        Log.i(TAG, "prepare: finsh");
    }

    public void startPush(String url) {
        videoPusher.startPush();
        audioPusher.startPush();
        pushNative.startPush(url);
    }

    public void stopPush() {
        videoPusher.stopPush();
        audioPusher.stopPush();
        pushNative.stopPush();
    }

    public void switchCamera() {
        videoPusher.switchCamera();
    }

    private void release() {
        videoPusher.release();
        audioPusher.release();
        pushNative.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated: ");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed: ");
        stopPush();
        release();
    }
}
