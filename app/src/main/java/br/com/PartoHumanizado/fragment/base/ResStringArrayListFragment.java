package br.com.PartoHumanizado.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.PartoHumanizado.R;

/**
 * Created by bruno on 04/12/14.
 */
public abstract class ResStringArrayListFragment extends BaseListFragment {

    private String[] stringArray;
    private String title;
    private int stringArrayId;

    public ResStringArrayListFragment(String title, @ArrayRes int stringArrayId) {
        this.title = title;
        this.stringArrayId = stringArrayId;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        stringArray = activity.getResources().getStringArray(this.stringArrayId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plano_de_parto_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateListView();

        FragmentActivity activity = getActivity();
        SharedPreferences preferences = activity.getSharedPreferences(getTitle(), Context.MODE_PRIVATE);
        ListView listView = getListView();

        for (int i = 0; i < listView.getCount(); i++)
            listView.setItemChecked(i, preferences.getBoolean(String.valueOf(i), false));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void updateListView() {
        ListView listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                listView.getContext(),
                R.layout.item_plano_de_parto,
                android.R.id.text1,
                stringArray);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean[] booleans = getCheckedBooleanArray();
        outState.putBooleanArray(getTitle(), booleans);
    }

    private boolean[] getCheckedBooleanArray() {
        ListView listView = getListView();
        int count = listView.getCount();
        boolean[] booleans = new boolean[count];
        for (int i = 0; i < count; i++) {
            booleans[i] = listView.isItemChecked(i);
        }
        return booleans;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null)
            return;
        boolean[] booleans = savedInstanceState.getBooleanArray(getTitle());
        ListView listView = getListView();
        for (int i = 0; i < booleans.length; i++) {
            listView.setItemChecked(i, booleans[i]);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        FragmentActivity activity = getActivity();
        SharedPreferences preferences = activity.getSharedPreferences(getTitle(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        boolean[] booleans = getCheckedBooleanArray();
        for (int i = 0; i < booleans.length; i++)
            edit.putBoolean(String.valueOf(i), booleans[i]);
        edit.commit();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
