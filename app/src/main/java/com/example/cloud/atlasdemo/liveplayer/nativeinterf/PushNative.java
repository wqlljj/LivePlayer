package com.example.cloud.atlasdemo.liveplayer.nativeinterf;

import android.util.Log;

/**
 * Created by cloud on 2019/6/12.
 */

public class PushNative {
    private String TAG = "Push/PN";
    static {
        System.loadLibrary("pushnative");
    }
        public native void startPush(String url);

    public native void stopPush();

    public native void release();

    /**
     * 设置视频参数
     * @param width
     * @param height
     * @param bitrate
     * @param fps
     */
    public native void setVideoOptions(int width, int height, int bitrate, int fps);

    /**
     * 设置音频参数
     * @param sampleRateInHz
     * @param channel
     */
    public native void setAudioOptions(int sampleRateInHz, int channel);


    /**
     * 发送视频数据
     * @param data
     */
    public native void fireVideo(byte[] data);

    /**
     * 发送音频数据
     * @param data
     * @param len
     */
    public native void fireAudio(byte[] data, int len);

//    public  void startPush(String url){
//        Log.i(TAG, "startPush: ");
//    }
//
//    public  void stopPush(){
//        Log.i(TAG, "stopPush: ");
//    }
//
//    public  void release(){
//        Log.i(TAG, "release: ");
//    }
//
//    public  void setVideoOptions(int width, int height, int bitrate, int fps){
//        Log.i(TAG, "setVideoOptions: ");
//    }
//
//    public  void setAudioOptions(int sampleRateInHz, int channel){
//        Log.i(TAG, "setAudioOptions: ");
//    }
//
//    public  void fireVideo(byte[] data){
//        Log.i(TAG, "fireVideo: ");
//    }
//
//    public  void fireAudio(byte[] data, int len){
//        Log.i(TAG, "fireAudio: ");
//    }
}
