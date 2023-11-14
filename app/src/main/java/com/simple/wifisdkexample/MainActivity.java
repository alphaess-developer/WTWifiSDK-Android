package com.simple.wifisdkexample;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alpha.wifisdk.WTWifiCenter;
import com.alpha.wifisdk.callback.Callback;
import com.alpha.wifisdk.callback.WTWifiCenterDelegate;
import com.alpha.wifisdk.model.WTRunningModel;
import com.alpha.wifisdk.model.WTSafetyModel;
import com.alpha.wifisdk.model.WTStaInfoModel;
import com.alpha.wifisdk.model.WTSystemModel;
import com.alpha.wifisdk.model.WTUpdateModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button queryWifiList,queryConnectInfo,updateEMSData;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> datas = new ArrayList<String>();
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryWifiList = findViewById(R.id.queryWifiList);
        queryConnectInfo = findViewById(R.id.queryConnectInfo);
        updateEMSData = findViewById(R.id.updateEMSData);
        listView = findViewById(R.id.wifiListLv);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInputDialog(datas.get(position));
            }
        });
        queryWifiList.setOnClickListener(this);
        queryConnectInfo.setOnClickListener(this);
        updateEMSData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.queryWifiList:
                queryWifiList();
                break;
            case R.id.queryConnectInfo:
                queryConnectInfo();
                break;
            case R.id.updateEMSData:
                updateEMSData();
                break;


        }

    }

    private void queryWifiList() {
        showProgressDialog();
        WTWifiCenter.getInstance(this).fetchWifiList(new Callback<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                hideProgressDialog();
                datas.clear();
                datas.addAll(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void queryConnectInfo(){
        showProgressDialog();
        WTWifiCenter.getInstance(this).loadWifiConfiguration(new Callback<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                hideProgressDialog();
                showConfigureDialog(result);
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateEMSData(){
        showProgressDialog();
        WTUpdateModel updateModel = new WTUpdateModel();
        updateModel.setACDC("1");
        WTWifiCenter.getInstance(this).updateEMSConfigurationWith(updateModel, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                hideProgressDialog();
            }

            @Override
            public void onError(Exception e) {
                hideProgressDialog();
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
    private void showInputDialog(String ssid){
        String connectTip = getString(R.string.connect_tip,ssid);
        final EditText inputServer = new EditText(MainActivity.this);
        inputServer.setHint(R.string.intput_password_tip);
        inputServer.setGravity(Gravity.CENTER);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(connectTip).setView(inputServer)
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String password = inputServer.getText().toString();
                if(password.trim().length() < 1){
                    Toast.makeText(MainActivity.this,getString(R.string.intput_password_tip),Toast.LENGTH_SHORT).show();
                }else{
                    WTWifiCenter.getInstance(MainActivity.this).wifiConfigurationWith(ssid, password.trim(), new Callback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean result) {
                            if(result){
                                Toast.makeText(MainActivity.this,getString(R.string.connect_success),Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this,getString(R.string.connect_false),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(MainActivity.this,getString(R.string.connect_false)+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        builder.show();
    }
    private void showConfigureDialog(Map<String, Object> result){
        boolean isExist = !TextUtils.isEmpty((String)result.get("ssid"));
        String title = isExist ? getString(R.string.exist_connect_info) : getString(R.string.no_connect_info);
        String msg = isExist ? getString(R.string.connect_info,result.get("ssid"),result.get("password"),(Boolean)result.get("state") ? getString(R.string.has_connect) : getString(R.string.not_connect)) : "";
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(msg)
                .setNegativeButton(getString(R.string.continue_connect), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton(getString(R.string.skip_connect), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                  startActivity(new Intent(MainActivity.this,SystemInfoActivity.class));
            }
        });
        builder.show();
    }



}