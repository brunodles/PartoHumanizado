package br.com.PartoHumanizado.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.util.TextAssetReader;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 27/11/14.
 */
public class TextFragment extends BaseFragment {

    private static final String TAG = "TextFragment";
    public static final String KEY_ASSET_FILENAME = "KEY_ASSET_FILENAME";
//    public static final String KEY_TITTLE = "KEY_TITTLE";

    private String tittle;
    private String text;
    private String menuTittle;

    @InjectView(R.id.titulo)
    TextView titulo;
    @InjectView(R.id.texto)
    TextView texto;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        tittle = getArguments().getString(KEY_TITTLE);
        String filename = getArguments().getString(KEY_ASSET_FILENAME);
        Log.d(TAG, "onAttach filename: " + filename);
        TextAssetReader textAssetReader = new TextAssetReader(activity, filename);
        textAssetReader.setLineBreaker("<br/>");
        textAssetReader.read();

        tittle = textAssetReader.getTittle();
        Log.d(TAG, "onAttach tittle " + tittle);
        text = textAssetReader.getString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, null);
        ButterKnife.inject(this, view);
        titulo.setText(tittle);
        texto.setText(Html.fromHtml(text));
        return view;
    }


    @Override
    public String getTitle() {
        return menuTittle;
    }

    public static final TextFragment create(String assetFileName, String menuTitle) {
        TextFragment fragment = new TextFragment();
        fragment.menuTittle = menuTitle;
        Bundle bundle = new Bundle();
//        bundle.putString(KEY_TITTLE, tittle);
        bundle.putString(KEY_ASSET_FILENAME, assetFileName);
        fragment.setArguments(bundle);
        return fragment;
    }
}
