package com.example.cloud.atlasdemo.liveplayer.params;

/**
 * Created by cloud on 2019/6/12.
 */

public class VideoParam {
    private int cameraId;
    private int width;
    private int height;

    public VideoParam(int width, int height, int cameraId) {
        this.width = width;
        this.height = height;
        this.cameraId = cameraId;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
