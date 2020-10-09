package com.jiangdg.usbcamera.application;

import android.app.Application;

import com.jiangdg.usbcamera.UVCCameraHelper;
import com.jiangdg.usbcamera.utils.CrashHandler;

/**application class
 *
 * Created by jianddongguo on 2017/7/20.
 */

public class MyApplication extends Application {
    private CrashHandler mCrashHandler;
    // File Directory in sd card
    public static final String DIRECTORY_NAME = "USBCamera";

    public static conf configuration = new conf();

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
