package br.com.PartoHumanizado.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

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

    private String tittleStr;
    private String textStr;
    private String menuTittle;
    private String imageFile;

    @InjectView(R.id.titulo)
    TextView titulo;
    @InjectView(R.id.texto)
    TextView texto;
    @InjectView(R.id.image)
    ImageView image;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        tittleStr = getArguments().getString(KEY_TITTLE);
        String filename = getArguments().getString(KEY_ASSET_FILENAME);
        Log.d(TAG, "onAttach filename: " + filename);
        TextAssetReader textAssetReader = new TextAssetReader(activity, filename);
        textAssetReader.setLineBreaker("<br/>");
        textAssetReader.read();

        imageFile = textAssetReader.getImage();
        tittleStr = textAssetReader.getTittle();
        Log.d(TAG, "onAttach tittleStr " + tittleStr);
        textStr = textAssetReader.getString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, null);
        ButterKnife.inject(this, view);
        titulo.setText(tittleStr);
        texto.setText(Html.fromHtml(textStr));
        new LoadImageAsyncTask(getActivity(), imageFile, image).execute();
        return view;
    }

    private class LoadImageAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private Context context;
        private String imageFile;
        private ImageView image;

        private LoadImageAsyncTask(Context context, String imageFile, ImageView image) {
            this.context = context;
            this.imageFile = imageFile;
            this.image = image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (imageFile == null)
                image.setVisibility(View.GONE);
            else
                image.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            InputStream is = null;
            try {
                is = context.getAssets().open(imageFile);
                return BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e(TAG, "doInBackground ", e);
            }
            cancel(true);
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null)
                image.setImageBitmap(bitmap);
        }
    }


    @Override
    public String getTitle() {
        return menuTittle;
    }

    public static final TextFragment create(String assetFileName, String menuTitle) {
        TextFragment fragment = new TextFragment();
        fragment.menuTittle = menuTitle;
        Bundle bundle = new Bundle();
//        bundle.putString(KEY_TITTLE, tittleStr);
        bundle.putString(KEY_ASSET_FILENAME, assetFileName);
        fragment.setArguments(bundle);
        return fragment;
    }
}
