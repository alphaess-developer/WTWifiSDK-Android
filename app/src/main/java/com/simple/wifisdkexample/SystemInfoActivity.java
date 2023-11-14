package com.simple.wifisdkexample;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.wifisdk.WTWifiCenter;
import com.alpha.wifisdk.callback.Callback;

import java.util.Map;

public class SystemInfoActivity extends Activity implements View.OnClickListener {
    private Button getSystemInfo, getRunningInfo,getExtendSystemInfo,getExtendRunningInfo;

    private Button getItalySafetyInfo;
    private ProgressDialog pd;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        getSystemInfo = findViewById(R.id.getSystemInfo);
        getRunningInfo = findViewById(R.id.getRunningInfo);
        getExtendSystemInfo = findViewById(R.id.getExtendSystemInfo);
        getExtendRunningInfo = findViewById(R.id.getExtendRunningInfo);
        getItalySafetyInfo = findViewById(R.id.getItalySafetyInfo);
        content = findViewById(R.id.content);
        getSystemInfo.setOnClickListener(this);
        getExtendSystemInfo.setOnClickListener(this);
        getRunningInfo.setOnClickListener(this);
        getExtendRunningInfo.setOnClickListener(this);
        getItalySafetyInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getSystemInfo:
                getSystemInfo();
                break;
            case R.id.getExtendSystemInfo:
                getExtendSystemInfo();
                break;
            case R.id.getRunningInfo:
                getRunningInfo();
                break;
            case R.id.getExtendRunningInfo:
                getExtendRunningInfo();
                break;
            case R.id.getItalySafetyInfo:
                getItalySafetyInfo();
                break;
        }

    }

    private void getSystemInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance().loadSystemInfo(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("systemInfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getExtendSystemInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance().loadSystemInfoByExtendProtocol(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("extendSystemInfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getRunningInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance().loadRunningInfo(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("runningInfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getExtendRunningInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance().loadRunningInfoByExtendProtocol(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("extendRunningInfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getItalySafetyInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance().loadAutoCheckInfoWithItalianSafety(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("safetyInfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showProgressDialog(){
        if(pd == null){
            pd = new ProgressDialog(this);
        }
        pd.setMessage(getString(R.string.loading));
        pd.show();
    }
    private void hideProgressDialog(){
        if(pd!=null && pd.isShowing()){
            pd.hide();
        }
    }
}