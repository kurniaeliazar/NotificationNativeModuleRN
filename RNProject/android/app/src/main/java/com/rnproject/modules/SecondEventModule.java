package com.rnproject.modules;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/**
 * Created by kurniaeliazar on 3/20/17.
 */

public class SecondEventModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactApplicationContext;

    public SecondEventModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactApplicationContext = reactContext;
    }

    @Override
    public String getName() {
        return "SecondEventModule";
    }

    @ReactMethod
    public void showDialog() {
        WritableMap params = Arguments.createMap();
        EventModule.sendEvent(reactApplicationContext, "showDialog", params);
    }

    @ReactMethod
    public void getStringSample(Promise promise) {
        promise.resolve("StringExample");
    }

    @ReactMethod
    public void getObjectSample(Promise promise) {
        WritableMap params = Arguments.createMap();
        params.putString("ObjectKey", "ObjectString");
        params.putString("ObjectKey2", "ObjectString2");
        promise.resolve(params);
    }

    @ReactMethod
    public void getArraySample(Promise promise){
        WritableArray array = Arguments.createArray();
        array.pushString("Array1");
        array.pushString("Array2");
        array.pushString("Array3");
        promise.resolve(array);
    }

    @ReactMethod
    public void sendArrayArguments(ReadableArray array, Promise promise){
        promise.resolve(array.getString(0));
    }

    @ReactMethod
    public void sendObjectArguments(ReadableMap obj, Promise promise){
        promise.resolve(obj.getString("ObjectKey3"));
    }

}
