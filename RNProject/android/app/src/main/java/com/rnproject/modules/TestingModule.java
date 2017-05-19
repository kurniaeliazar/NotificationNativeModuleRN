package com.rnproject.modules;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kurniaeliazar on 5/16/17.
 */

public class TestingModule extends ReactContextBaseJavaModule {

    public static final String TAG = "TestingModule" ;
    private ReactApplicationContext reactContext;

    public TestingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(TAG, "Hello Sense");
        return constants;
    }

    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void showArgumentsExample(String stringArg, boolean booleanArg, double doubleArg,
                                     ReadableMap readableMapArg, ReadableArray readableArrayArg){
        Log.d(TAG, "---------------- showArgumentsExample ------------------- ");
        Log.d(TAG, "string args: " + stringArg);
        Log.d(TAG, "boolean args: " + booleanArg);
        Log.d(TAG, "double args: " + doubleArg);

        Log.d(TAG, "---------------- readableMap Arguments ------------------ ");

        ReadableMapKeySetIterator iterator = readableMapArg.keySetIterator();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            String value = readableMapArg.getString(key);
            Log.d(TAG, "key : " + key + " | " + "value : " + value);
        }

        Log.d(TAG, "---------------- readableArray Arguments ------------------ ");

        for(int i = 0; i < readableArrayArg.size(); i++){
            Log.d(TAG, readableArrayArg.getString(i));
        }
    }

    @ReactMethod
    public void promiseExample(int param, Promise promise){
        try{
            int result = 8 / param;
            promise.resolve(result);
        }catch (ArithmeticException ex){
            promise.reject(TAG + "error",  ex.getMessage());
        }
    }

    @ReactMethod
    public void sendEvents(){
        Log.d(TAG, "sendEvents: ");
        WritableMap data = Arguments.createMap();
        WritableArray content = Arguments.createArray();

        content.pushString("content 1");
        content.pushString("content 2");

        data.putString("id", "001");
        data.putArray("content", content);

        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(TAG, data);
    }
}
