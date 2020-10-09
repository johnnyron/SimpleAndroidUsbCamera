package com.jiangdg.usbcamera.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jiangdg.usbcamera.R;
import com.jiangdg.usbcamera.application.MyApplication;

import java.util.ArrayList;
import java.util.List;


public class WaitingActivity extends AppCompatActivity {
    private AlertDialog mDialog;
    private AlertDialog mDialog2;

    private List<String> getResolutionList() {

        List<String> resolutions = new ArrayList<String>();
        resolutions.add("1920x1080");
        resolutions.add("1600x1200");
        resolutions.add("1280x720");
        resolutions.add("640x480");

        return resolutions;
    }

    private List<String> getRatioList() {

        List<String> resolutions = new ArrayList<String>();
        resolutions.add("2.50:1");
        resolutions.add("2.39:1");
        resolutions.add("2.35:1");
        resolutions.add("2.20:1");
        resolutions.add("2:1");
        resolutions.add("1.85:1");
        resolutions.add("16:9");

        return resolutions;
    }

    private void startDialog2()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(WaitingActivity.this);
        View rootView = LayoutInflater.from(WaitingActivity.this).inflate(R.layout.layout_dialog_list, null);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_dialog);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WaitingActivity.this, android.R.layout.simple_list_item_1, getRatioList());
        if (adapter != null) {
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final String resolution = (String) adapterView.getItemAtPosition(position);
                String[] tmp = resolution.split(":");
                if (tmp != null && tmp.length >= 2) {
                    float width = Float.valueOf(tmp[0]);
                    float height = Float.valueOf(tmp[1]);
                    MyApplication.configuration.setStretchRatio(width/height);

                }
                mDialog2.dismiss();

                startMainActivity();
            }
        });

        builder.setView(rootView);
        mDialog2 = builder.create();
        mDialog2.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.waiting);


        AlertDialog.Builder builder = new AlertDialog.Builder(WaitingActivity.this);
        View rootView = LayoutInflater.from(WaitingActivity.this).inflate(R.layout.layout_dialog_list, null);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_dialog);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WaitingActivity.this, android.R.layout.simple_list_item_1, getResolutionList());
        if (adapter != null) {
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final String resolution = (String) adapterView.getItemAtPosition(position);
                String[] tmp = resolution.split("x");
                if (tmp != null && tmp.length >= 2) {
                    int width = Integer.valueOf(tmp[0]);
                    int height = Integer.valueOf(tmp[1]);
                    MyApplication.configuration.setHdmiRes(width, height);

                }
                mDialog.dismiss();

                startDialog2();
            }
        });

        builder.setView(rootView);
        mDialog = builder.create();
        mDialog.show();


    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WaitingActivity.this, USBCameraActivity.class));
                WaitingActivity.this.finish();
            }
        }, 100);
    }
}

