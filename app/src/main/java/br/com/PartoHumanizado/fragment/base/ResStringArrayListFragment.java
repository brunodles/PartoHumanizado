package br.com.PartoHumanizado.fragment.base;

import android.support.annotation.ArrayRes;

/**
 * Created by bruno on 04/12/14.
 */
public abstract class ResStringArrayListFragment extends BaseListFragment {
    public void setStringArray(@ArrayRes int stringArrayId) {
        String[] stringArray = getActivity().getResources().getStringArray(stringArrayId);
    }
}
