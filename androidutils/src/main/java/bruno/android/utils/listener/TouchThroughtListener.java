package bruno.android.utils.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dev on 05/11/2014.
 */
public class TouchThroughtListener implements View.OnTouchListener {

    private static final String TAG = "TouchThrought";
    private View anotherView;

    public TouchThroughtListener(View anotherView) {
        this.anotherView = anotherView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        boolean b = false;
//        if (!b)
//        b |= anotherView.onTouchEvent(event);
//        if (!b)
//            b |= v.onTouchEvent(event);
        return anotherView.dispatchTouchEvent(event);
    }
}
