package com.jiangdg.usbcamera.application;

import com.serenegiant.usb.widget.UVCCameraTextureView;

public class conf {

    private int mWidth = 1920;
    private int mHeight = 1080;
    private float mRatio = 2.35f;

    public int getWidth()
    {
        return mWidth;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public float getRatio()
    {
        return mRatio;
    }

    public void setHdmiRes(int width, int height) {
        mWidth = width;
        mHeight = height;
        UVCCameraTextureView.sHdmiRatio = width/height;
    }

    public void setStretchRatio(float ratio) {
        mRatio = ratio;
        UVCCameraTextureView.sRatio = ratio;
    }
}
