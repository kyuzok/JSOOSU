package com.spicystar.jsoosu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.security.auth.callback.Callback;

public class PhotosFragment extends Fragment {

    public WebView photoWebView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        photoWebView = v.findViewById(R.id.webview);
        //enables javascript
        photoWebView.getSettings().setJavaScriptEnabled(true);
        //need this to open the url
        photoWebView.setWebViewClient(new WebViewClient());
        photoWebView.loadUrl("https://www.instagram.com/osujso/");


        return v;
    }


}
