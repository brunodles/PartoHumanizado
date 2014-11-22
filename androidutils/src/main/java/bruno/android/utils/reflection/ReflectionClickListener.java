package bruno.android.utils.reflection;

import android.view.View;

/**
 * This class intent to simplify access to activity's buttons.
 *
 * Created by bruno on 17/08/14.
 */
public class ReflectionClickListener extends Reflector implements View.OnClickListener{

    private static final String TAG = "ReflectionListener";

    public ReflectionClickListener(Object object, String methodName) {
        super(object, methodName);
        tryToFindMethodByName();
    }

    @Override
    public void onClick(View v) {
        invoke();
    }

}