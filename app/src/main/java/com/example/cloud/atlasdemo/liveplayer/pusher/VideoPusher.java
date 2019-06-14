package com.example.cloud.atlasdemo.liveplayer.pusher;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.cloud.atlasdemo.liveplayer.nativeinterf.PushNative;
import com.example.cloud.atlasdemo.liveplayer.params.VideoParam;

import java.io.IOException;

/**
 * Created by cloud on 2019/6/12.
 */

class VideoPusher implements Pusher, SurfaceHolder.Callback, Camera.PreviewCallback {
    private SurfaceHolder holder;
    private VideoParam videoParam;
    private PushNative pushNative;
    private boolean isPushing = false;
    private Camera camera;
    private byte[] buffers;
    private String TAG = "Push/VP";

    public VideoPusher(SurfaceHolder holder, VideoParam videoParam, PushNative pushNative) {
        this.holder = holder;
        this.videoParam = videoParam;
        this.pushNative = pushNative;
        holder.addCallback(this);
        Log.i(TAG, "VideoPusher: ");
    }

    public void switchCamera() {
        if(videoParam.getCameraId() == Camera.CameraInfo.CAMERA_FACING_FRONT){
            videoParam.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
        }else{
            videoParam.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }
        //重新预览
        stopPreview();
        startPreview();
    }

    public void startPush() {
        Log.i(TAG, "startPush: ");
        isPushing = true;
    }

    public void stopPush() {
        isPushing = false;
    }

    public void release() {
        stopPreview();
    }

    private void stopPreview() {
        if(camera != null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated: ");
        startPreview();
    }

    private void startPreview() {
        try {
            camera = Camera.open(videoParam.getCameraId());
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(videoParam.getWidth(),videoParam.getHeight());
            camera.setParameters(parameters);
            //buffers空间不够不回调，注意预览PreviewSize大小
            buffers = new byte[videoParam.getWidth()*videoParam.getHeight()*4];
            camera.setPreviewCallbackWithBuffer(this);
            camera.addCallbackBuffer(buffers);
            if(videoParam.getCameraId() == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                camera.setDisplayOrientation(180);
            }
            camera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed: ");
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if(camera != null){
            camera.addCallbackBuffer(data);
        }

        if(isPushing){
            //回调函数中获取图像数据，然后给Native代码编码
            pushNative.fireVideo(data);
        }
    }
}
