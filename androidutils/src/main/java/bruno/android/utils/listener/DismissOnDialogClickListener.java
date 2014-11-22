package bruno.android.utils.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by dev on 16/10/2014.
 */
public class DismissOnDialogClickListener implements AlertDialog.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
