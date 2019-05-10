package com.spicystar.jsoosu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OfficersFragment extends Fragment {

    public WebView officerWebView;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_officers, container, false);
        officerWebView = v.findViewById(R.id.webview);
        //enables javascript
        officerWebView.getSettings().setJavaScriptEnabled(true);
        //need this to open the url
        officerWebView.setWebViewClient(new WebViewClient());
        officerWebView.loadUrl("http://org.osu.edu/jso/about/officers/");

        return v;


    }
}
