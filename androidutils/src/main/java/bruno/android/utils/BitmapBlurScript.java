package bruno.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

/**
 * Created by dev on 19/11/2014.
 */
public class BitmapBlurScript {

    private static final int MAX_RADIUS_VALUE = 25;
    private Context context;
    private float radius = 25f;
    private boolean mustRecycle = true;
    private Bitmap bitmap;

    public BitmapBlurScript(Context context, Bitmap bitmap) {
        this.context = context;
        this.bitmap = bitmap;
    }

    public Bitmap blurBitmap() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return processMultiBlur(bitmap);
        else
            return bitmap;
    }

    public void blurBitmap(OnBlurCompletedListener blurCompleted) {
        new BlurAsyncTask(blurCompleted).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap processMultiBlur(Bitmap bitmap) {
        float currentRadius = radius;
        Bitmap result = bitmap;
        while (currentRadius > 0) {
            float nextRadius;
            if (currentRadius > MAX_RADIUS_VALUE)
                nextRadius = MAX_RADIUS_VALUE;
            else
                nextRadius = currentRadius;
            result = blurBitmap(result, nextRadius);
            currentRadius -= nextRadius;
        }
        return result;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap blurBitmap(Bitmap bitmap, float radius) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context.getApplicationContext());

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        if (mustRecycle)
            bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;
    }

    public BitmapBlurScript setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public BitmapBlurScript setMustRecycle(boolean mustRecycle) {
        this.mustRecycle = mustRecycle;
        return this;
    }

    public static Bitmap bitmapFromImageView(ImageView imageView) {
        return ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    }

    private class BlurAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        OnBlurCompletedListener listener;

        private BlurAsyncTask(OnBlurCompletedListener listener) {
            this.listener = listener;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                return processMultiBlur(bitmap);
            } catch (Exception e) {
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            listener.onCompleted(bitmap);
        }
    }

    public interface OnBlurCompletedListener {
        void onCompleted(Bitmap bitmap);
    }
}
