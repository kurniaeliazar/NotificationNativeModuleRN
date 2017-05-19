package com.rnproject.modules;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by kurniaeliazar on 5/17/17.
 */

public class TestingJSModule extends ReactContextBaseJavaModule {

    public TestingJSModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "IntegrationModule";
    }

    @ReactMethod
    public void showArg(){

    }
}
