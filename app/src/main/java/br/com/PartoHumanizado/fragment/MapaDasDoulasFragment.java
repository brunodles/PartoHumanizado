package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by bruno on 22/11/14.
 */
public class MapaDasDoulasFragment extends WebViewFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadUrl("https://www.google.com/maps/d/u/0/viewer?mid=z21ZkzzfAD2Q.ktSykA7yVPMs");
        return view;
    }

    @Override
    public String getTitle() {
        return "Mapa das Doulas";
    }
}
