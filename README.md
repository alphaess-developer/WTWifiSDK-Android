## WTWifiSDK

WTWifiSDK is a Framework for wifi configuration  **for** alphaess.

## 🔨 Requirements

The system requirement for WTWifiSDK is Android 5.0

## Install

* Place wifisdk-2.1.5.aar in the libs directory of the project into the libs directory of your own project

  
  
* Add the following configuration to the module's build.gradle file.

  ```groovy
  dependencies {
  	implementation fileTree(dir: 'libs', include: ['*.aar'])
  }
  ```

  

## How to use?

* You can get device serial number from ap, for example:

  ```java
      WTWifiCenter.getInstance().fetchSystemSN(new Callback<String>() {
              @Override
              public void onSuccess(String s) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* Get the wifi ssid list from wifi collector device, for example:

  ```java
         WTWifiCenter.getInstance().fetchWifiList(new Callback<List<String>>() {
              @Override
              public void onSuccess(List<String> strings) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* And if you want to config the local wifi for energy storage device, use this api:

  ```java
        WTWifiCenter.getInstance().wifiConfigurationWith("ssid", "password", new Callback<Boolean>() {
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
     WTWifiCenter.getInstance().loadWifiConfiguration(new Callback<Map<String, Object>>() {
              @Override
              public void onSuccess(Map<String, Object> stringObjectMap) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* How to load the system information of energy management system?

  **Just call this api:**

  The result will be called back by `loadSystemInfo`.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
        WTWifiCenter.getInstance().loadSystemInfo(new Callback<Map<String, String>>() {
              @Override
              public void onSuccess(Map<String, String> stringStringMap) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

  **With the extend information:**

  We provide query methods for extended parameters as follows:

  ```java
      WTWifiCenter.getInstance().loadSystemInfoByExtendProtocol(new Callback<Map<String, String>>() {
              @Override
              public void onSuccess(Map<String, String> stringStringMap) {
  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* How to load the running information of energy management system?

  **Just call this api:**

  The result will be called back by `loadRunningInfo`.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
       WTWifiCenter.getInstance().loadRunningInfo(new Callback<Map<String, String>>() {
              @Override
              public void onSuccess(Map<String, String> stringStringMap) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

  **With the extend information:**

  We provide query methods for extended parameters as follows:

  ```java
        WTWifiCenter.getInstance().loadRunningInfoByExtendProtocol(new Callback<Map<String, String>>() {
              @Override
              public void onSuccess(Map<String, String> stringStringMap) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* How to load the italian safety self-check information of energy management system?

  **Just call this api:**

  The result will be called back by `loadAutoCheckInfoWithItalianSafety`.But it should be noted that this is not a very stable callback, you may try to send the command multiple times after ensuring that the direct connection is successful.

  ```java
         WTWifiCenter.getInstance().loadAutoCheckInfoWithItalianSafety(new Callback<Map<String, String>>() {
              @Override
              public void onSuccess(Map<String, String> stringStringMap) {
                  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

* How to configuration the energy storage device with parameters?

  ```java
   WTUpdateModel updateModel = new WTUpdateModel();
          updateModel.setACDC("1");
          WTWifiCenter.getInstance().updateEMSConfiguration(updateModel, new Callback<Boolean>() {
              @Override
              public void onSuccess(Boolean result) {
                
              }
  
              @Override
              public void onError(Exception e) {
                 
              }
          });
  ```

  **With the extend parameters:**

  We provide update methods for extended parameters as follows:

  ```java
  WTUpdateExtendModel updateModel = new WTUpdateExtendModel();
          updateModel.setOnGridPower("1024");
          WTWifiCenter.getInstance().updateEMSConfigurationByExtendProtocol(updateModel, new Callback<Boolean>() {
              @Override
              public void onSuccess(Boolean result) {
  
              }
  
              @Override
              public void onError(Exception e) {
  
              }
          });
  ```

## Issues

If you have any questions about the sdk, we welcome you to open issues. But when opening issues, please describe the problem replay scenario as clearly as possible, so that we can help you solve the problem more efficiently.

## Feature

We will support querying self-test information through EMS, including meter self-test information and battery self-test information. Although the SDK currently provides this API `loadAutoCheckInfo`, since the EMS system does not currently support it, you will always get null through this API. But don't worry, we will work on it all the time!

If you have any questions, you can contact me anytime.
