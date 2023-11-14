## WTWifiSDK

WTWifiSDK is a Framework for wifi configuration  **for** alphaess.

## 🔨 Requirements

The system requirement for WTWifiSDK is Android 5.0

## Install

* Add the following configuration to the build.gradle file under the project.

  ```groovy
  allprojects {
      repositories {
        maven { url 'https://jitpack.io' }
      }
  }
  ```

* Add the following configuration to the module's build.gradle file.

  ```groovy
  dependencies {
  	implementation 'com.github.alphaess-developer:WTWifiSDK:1.0.0'
  }
  ```

* Add android:usesCleartextTraffic="true" to the AndroidManifest.xml file

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <manifest ...>
      <application
          ...
          android:usesCleartextTraffic="true"
          ...>
          ...
      </application>
  </manifest>
  ```

  

## How to use?

* Add delegate with `WTWifiCenterDelegate`, for example:

  ```java
          WTWifiCenter.getInstance(this).addWifiProtocolListener(new WTWifiCenterDelegate() {
              @Override
              public void didDisconnectedWith(String s) {
  
              }
  
              @Override
              public void didConnectedWith(String s) {
  
              }
  
              @Override
              public void didReceiveEMSSystemInfo(WTSystemModel wtSystemModel) {
  
              }
  
              @Override
              public void didReceiveEMSRunningInfo(WTRunningModel wtRunningModel) {
  
              }
  
              @Override
              public void didReceiveEMSSafetyInfo(WTSafetyModel wtSafetyModel) {
  
              }
  
              @Override
              public void didUpdateEMSParametersSuccess() {
  
              }
              @Override
              public void receiveInfoErr(Exception e) {
  
              }
          });
  ```

  

* Start the wifi configuration when the configuration view will appear, and release the wifi configuration when the view dealloc,  for example:

  ```java
  WTWifiCenter.getInstance(this).startConfiguration();
  WTWifiCenter.getInstance(this).releaseConfiguration();
  ```

  

* Get the wifi ssid list from wifi collector device, for example:

  ```java
  WTWifiCenter.getInstance(this).fetchWifiList(new Callback<List<String>>() {
       @Override
       public void onSuccess(List<String> wfList) {
  
       }
  
       @Override
       public void onError(Exception e) {
  
       }
  });
  ```

  

  

* And if you want to config the local wifi for energy storage device, use this api:

  ```java
  WTWifiCenter.getInstance(MainActivity.this).wifiConfigurationWith("ssid", "password", new Callback<Boolean>() {
       @Override
       public void onSuccess(Boolean aBoolean) {
                           
       }
       @Override
       public void onError(Exception e) {
                              
       }
   });    
  ```

  

* If you want to see historical configuration then use Api like this：

  ```java
  WTWifiCenter.getInstance(this).loadWifiConfiguration(new Callback<WTStaInfoModel>() {
      @Override
      public void onSuccess(WTStaInfoModel wtStaInfoModel) {
                  
      }
  
      @Override
      public void onError(Exception e) {
                  
      }
  });
  ```

  

* How to load the system information of energy management system?

  **Step one:**

  Send EMS commands to query the system information of energy storage devices.

  ```java
  WTWifiCenter.getInstance(this).sendSystemInfoCommand();
  ```

  **Step two:**

  The result will be called back by [didReceiveEMSSystemInfo] in WTWifiCenterDelegate.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
   @Override
   public void didReceiveEMSSystemInfo(WTSystemModel wtSystemModel) {
              
   }
  ```

* How to load the running information of energy management system?

  **Step one:**

  Send EMS commands to query the running information of energy storage devices.

  ```java
  WTWifiCenter.getInstance(this).sendRuningInfoCommand();
  ```

  **Step two:**

  The result will be called back by [didReceiveEMSRunningInfo] in WTWifiCenterDelegate.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
  @Override
  public void didReceiveEMSRunningInfo(WTRunningModel wtRunningModel) {
                  
  }
  ```

* How to load the safety information of energy management system?

  **Step one:**

  Send EMS commands to query the safety information of energy storage devices.

  ```java
  WTWifiCenter.getInstance(this).sendSafetyInfoCommand();
  ```

  **Step two:**

  The result will be called back by [didReceiveEMSSafetyInfo] in WTWifiCenterDelegate.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
  @Override
  public void didReceiveEMSSafetyInfo(WTSafetyModel wtSafetyModel) {
                  
  }
  ```

* And the last question, how to configuration the energy storage device with parameters?

  ```java
  WTUpdateModel updateModel = new WTUpdateModel();
  updateModel.setACDC("3");
  updateModel.setCTRate("1");
  updateModel.setOnGridCap("5000");
  ...        
  WTWifiCenter.getInstance(this).updateEMSConfigurationWith(updateModel);
  ```

  **Note:**

  When the configuration success, the delegate method [didUpdateEMSParametersSuccess] will be called. And otherwise, the delegate method [didUpdateEMSParametersFailed] will be called.

## Issues

If you have any questions about the sdk, we welcome you to open issues. But when opening issues, please describe the problem replay scenario as clearly as possible, so that we can help you solve the problem more efficiently.

## Feature

We will work on a more stable implementation.



