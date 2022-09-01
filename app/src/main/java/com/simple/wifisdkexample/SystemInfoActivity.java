package com.simple.wifisdkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.wifisdk.WTWifiCenter;
import com.alpha.wifisdk.callback.WTWifiCenterDelegate;
import com.alpha.wifisdk.model.WTRunningModel;
import com.alpha.wifisdk.model.WTSafetyModel;
import com.alpha.wifisdk.model.WTSystemModel;
import com.google.gson.Gson;

public class SystemInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button getSystemInfo, getRunningInfo,getSafeInfo;
    private ProgressDialog pd;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        getSystemInfo = findViewById(R.id.getSystemInfo);
        getRunningInfo = findViewById(R.id.getRunningInfo);
        getSafeInfo = findViewById(R.id.getSafeInfo);
        content = findViewById(R.id.content);
        getSystemInfo.setOnClickListener(this);
        getRunningInfo.setOnClickListener(this);
        getSafeInfo.setOnClickListener(this);
        WTWifiCenter.getInstance(this).startConfiguration();
        WTWifiCenter.getInstance(this).addWifiProtocolListener(new WTWifiCenterDelegate() {
            @Override
            public void didDisconnectedWith(String s) {

            }

            @Override
            public void didConnectedWith(String s) {

            }

            @Override
            public void didReceiveEMSSystemInfo(WTSystemModel wtSystemModel) {
                hideProgressDialog();
                content.setText(new Gson().toJson(wtSystemModel));

            }

            @Override
            public void didReceiveEMSRunningInfo(WTRunningModel wtRunningModel) {
                hideProgressDialog();
                content.setText(new Gson().toJson(wtRunningModel));

            }

            @Override
            public void didReceiveEMSSafetyInfo(WTSafetyModel wtSafetyModel) {
                hideProgressDialog();
                content.setText(new Gson().toJson(wtSafetyModel));

            }

            @Override
            public void didUpdateEMSParametersSuccess() {

            }

            @Override
            public void receiveInfoErr(Exception e) {
                hideProgressDialog();
                Toast.makeText(SystemInfoActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
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
            case R.id.getSafeInfo:
                getSafeInfo();
                break;
        }

    }

    private void getSystemInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).sendSystemInfoCommand();

    }
    private void getRunningInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).sendRuningInfoCommand();

    }
    private void getSafeInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).sendSafetyInfoCommand();

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