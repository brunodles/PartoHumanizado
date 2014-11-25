package br.com.PartoHumanizado.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 22/11/14.
 */
public abstract class WebViewFragment extends BaseFragment {
    @InjectView(br.com.PartoHumanizado.R.id.webView)
    WebView webView;
    @InjectView(br.com.PartoHumanizado.R.id.progress)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(br.com.PartoHumanizado.R.layout.fragment_denuncie, null);
        ButterKnife.inject(this, view);
        webView.getSettings().setJavaScriptEnabled(true);
//        getActivity().getWindow().requestFeature(Window.FEATURE_PROGRESS);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100)
                    progressBar.setVisibility(View.VISIBLE);
                Log.d("progress", "progress " + progress);
                progressBar.setProgress(progress);
                if (progress >= 100)
                    progressBar.setVisibility(View.GONE);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }
}
