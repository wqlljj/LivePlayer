package com.example.cloud.atlasdemo.liveplayer;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.cloud.atlasdemo.liveplayer.pusher.LivePusher;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private LivePusher live;
    static final String URL = "rtmp://112.74.96.116/live/jason";
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityPermissionsDispatcher.needPermWithPermissionCheck(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("pushnative");
    }

    public void mStartLive(View view) {
        Log.i(TAG, "mStartLive: "+stringFromJNI());
        Button btn = (Button)view;
        if(btn.getText().equals("开始直播")){
            live.startPush(URL);
            btn.setText("停止直播");
        }else{
            live.stopPush();
            btn.setText("开始直播");
        }
    }

    public void mSwitchCamera(View view) {
        live.switchCamera();
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needPerm() {
        Log.i(TAG, "needPerm: ");
//        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
        //相机图像的预览
        SurfaceView view = new SurfaceView(this.getBaseContext());
        view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ((FrameLayout) findViewById(R.id.video_preview)).addView(view);
        live = new LivePusher(view.getHolder());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        Log.i(TAG, "onShowRationale: ");
    }

}
