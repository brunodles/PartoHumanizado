package br.com.PartoHumanizado.fragment;

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

import br.com.PartoHumanizado.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 22/11/14.
 */
public class DenucieFragment extends BaseFragment {

    @InjectView(R.id.webView)
    WebView webView;
    @InjectView(R.id.progress)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denuncie, null);
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


        webView.loadUrl("http://cidadao.mpf.mp.br/formularios/formulario-eletronico");
        return view;
    }

    @Override
    public String getTitle() {
        return "Denuncie";
    }
}
