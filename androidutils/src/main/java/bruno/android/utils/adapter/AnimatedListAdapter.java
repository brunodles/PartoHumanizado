package bruno.android.utils.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;

public class AnimatedListAdapter implements ListAdapter {

	ListAdapter listAdapter;
	int animation_id;
	int lastAnimatedPosition = -1;
	boolean infinityAnimation = false;

	public AnimatedListAdapter(ListAdapter listAdapter, int animation_id) {
		super();
		this.listAdapter = listAdapter;
		this.animation_id = animation_id;
	}
	
	public AnimatedListAdapter withiInfinityAnimation(){
		infinityAnimation = true;
		return this;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View view = listAdapter.getView(position, convertView, parent);
        if ((!infinityAnimation) && (position <= lastAnimatedPosition))
			return view;

        Animation animation = AnimationUtils.loadAnimation(parent.getContext(),
				animation_id);
		animation.setDuration(300);
		view.startAnimation(animation);
		lastAnimatedPosition = position;
		animation = null;
		return view;
	}

	@Override
	public int getCount() {
		return listAdapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		return listAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return listAdapter.getItemId(position);
	}

	@Override
	public int getItemViewType(int position) {
		return listAdapter.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return listAdapter.getViewTypeCount();
	}

	@Override
	public boolean hasStableIds() {
		return listAdapter.hasStableIds();
	}

	@Override
	public boolean isEmpty() {
		return listAdapter.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		listAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		listAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return listAdapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int arg0) {
		return listAdapter.isEnabled(arg0);
	}

}
