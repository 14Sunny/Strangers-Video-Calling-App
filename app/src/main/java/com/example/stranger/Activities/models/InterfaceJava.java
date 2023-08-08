package com.example.stranger.Activities.models;

import android.telecom.Call;
import android.webkit.JavascriptInterface;

import com.example.stranger.Activities.CallActivity;

public class InterfaceJava {
    CallActivity callActivity;

    public InterfaceJava (CallActivity callActivity){
        this.callActivity=callActivity;
    }

    @JavascriptInterface
    public void onPeerConnected(){
        callActivity.onPeerConnected();
    }
}
