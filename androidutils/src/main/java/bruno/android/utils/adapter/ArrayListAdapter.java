package bruno.android.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ArrayListAdapter<ItemClass> extends BaseAdapter {
	// public abstract class ListAdapter<T> extends BaseAdapter {

	protected int itemLayout;
	protected List<ItemClass> objects;

	//
	// protected OnClickListener clickListener;
	// protected OnLongClickListener longClickListener;

	/**
	 * Lembre de passar o Layout, ele sera "inflado" automaticamente
	 */
	public ArrayListAdapter(int layout, List<ItemClass> objects) {
		super();
		this.itemLayout = layout;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null)
			row = inflate(parent);

		// Ao setar o clickListener na view, impede o android de realcar
		// a view selecionada, acabava com o estilo da aplicacao
		// row.setOnClickListener(this);
		// row.setOnLongClickListener(this);

		return getItemView(position, row, parent, objects.get(position));
	}

	private View inflate(ViewGroup parent) {
		View row;
		LayoutInflater inflater = getLayoutInflater(parent);
		row = inflater.inflate(itemLayout, parent, false);
		return row;
	}

	protected LayoutInflater getLayoutInflater(ViewGroup parent) {
		return (LayoutInflater) parent.getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		// Activity activity = (Activity) context;
		// LayoutInflater inflater = activity.getLayoutInflater();
		// return inflater;
	}

	abstract protected View getItemView(int position, View row,
			ViewGroup parent, ItemClass object);

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}
	
	public List<ItemClass> getList(){
		return objects;
	}
}
