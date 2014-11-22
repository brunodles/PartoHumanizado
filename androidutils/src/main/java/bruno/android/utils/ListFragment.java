package bruno.android.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;

import java.util.List;

public abstract class ListFragment<Model> extends
		android.support.v4.app.ListFragment implements OnItemClickListener,
		OnItemLongClickListener {

	private ListClickListener<Model> listener;
	OnItemClickListener onItemClickListener;
	OnItemLongClickListener onItemLongClickListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setHasOptionsMenu(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
		getListView().setOnItemClickListener(this);
		getListView().setOnItemLongClickListener(this);
		refresh();
	}

	private void refresh() {
		if (listener != null)
			listener.onRefresh();
	}

	public void setListener(ListClickListener<Model> listener) {
		this.listener = listener;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public void setOnItemLongClickListener(
			OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	public void setList(List<Model> objects) {
		setListAdapter(listToAdapter(objects));
	}

	protected abstract ListAdapter listToAdapter(List<Model> objects);

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		@SuppressWarnings("unchecked")
		Model model = (Model) parent.getAdapter().getItem(position);
		if (listener != null)
			listener.onClick(model);
		if (onItemClickListener != null)
			onItemClickListener.onItemClick(parent, view, position, id);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		if (onItemLongClickListener != null)
			return onItemLongClickListener.onItemLongClick(parent, view,
					position, id);
		return false;
	}

	public static interface ListClickListener<Model> {
		public void onClick(Model model);

		public void onRefresh();
	}
}
