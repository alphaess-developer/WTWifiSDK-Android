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
    private Button getSystemInfo, getRunningInfo,getSelfCheckInfo;
    private ProgressDialog pd;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        getSystemInfo = findViewById(R.id.getSystemInfo);
        getRunningInfo = findViewById(R.id.getRunningInfo);
        getSelfCheckInfo = findViewById(R.id.getSelfCheckInfo);
        content = findViewById(R.id.content);
        getSystemInfo.setOnClickListener(this);
        getRunningInfo.setOnClickListener(this);
        getSelfCheckInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getSystemInfo:
                getSystemInfo();
                break;
            case R.id.getRunningInfo:
                getRunningInfo();
                break;
            case R.id.getSelfCheckInfo:
                getSelfCheckInfo();
                break;
        }

    }

    private void getSystemInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).loadSystemInfo(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("systeminfo:"+result.toString());
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
        WTWifiCenter.getInstance(this).loadRunningInfo(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("runninginfo:"+result.toString());
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getSelfCheckInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).loadSelfCheckInfo(new Callback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> result) {
                hideProgressDialog();
                content.setText("loadSelfCheckInfo:"+result.toString());
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