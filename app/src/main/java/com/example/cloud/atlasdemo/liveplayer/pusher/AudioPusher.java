package com.example.cloud.atlasdemo.liveplayer.pusher;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.example.cloud.atlasdemo.liveplayer.nativeinterf.PushNative;
import com.example.cloud.atlasdemo.liveplayer.params.AudioParam;

/**
 * Created by cloud on 2019/6/12.
 */

class AudioPusher implements Pusher{
    private String TAG = "Push/AP";
    private final int minBufferSize;
    private AudioParam audioParam;
    private PushNative pushNative;
    private AudioRecord audioRecord;
    private boolean isPushing = false;

    public AudioPusher(AudioParam audioParam, PushNative pushNative) {
        this.audioParam = audioParam;
        this.pushNative = pushNative;

        int channelConfig = audioParam.getChannel() == 1 ?
                AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO;
        minBufferSize = AudioRecord.getMinBufferSize(audioParam.getSampleRateInHz(),channelConfig,
                AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                audioParam.getSampleRateInHz(),
                channelConfig,
                AudioFormat.ENCODING_PCM_16BIT,minBufferSize);
        Log.i(TAG, "AudioPusher: ");
    }

    public void startPush() {
        isPushing = true;
        new Thread(new AudioTask()).start();
    }

    public void stopPush() {
        isPushing = false;
        audioRecord.stop();
    }

    public void release() {
        if(audioRecord!=null){
            audioRecord.release();
            audioRecord = null;
            audioParam = null;
        }
    }

    private class AudioTask implements Runnable {

        @Override
        public void run() {
            try{

                // 防止某些手机崩溃，例如联想

                audioRecord.startRecording();
                Log.i(TAG, "startRecording: ");
            }catch (IllegalStateException e){
                Log.e(TAG, "IllegalStateException: " +e.getMessage());

            }
            while (isPushing){
                byte[] buffer = new byte[minBufferSize];
                int len = audioRecord.read(buffer,0,minBufferSize);
                if(len>0){
                    pushNative.fireAudio(buffer,len);
                }
            }
        }
    }
}
