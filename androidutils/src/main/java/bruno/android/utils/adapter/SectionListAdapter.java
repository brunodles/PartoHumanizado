package bruno.android.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SectionListAdapter<ItemClass, SectionClass> extends
		BaseAdapter {

	private static final int VIEW_TYPE_SECTION = 0;
	private static final int VIEW_TYPE_ITEM = 1;

	protected int itemLayout;
	protected int sectionrLayout;
	List<ItemClass> items;
	List<SectionClass> sections;
	List<Object> objects;

	public SectionListAdapter(int itemLayout, int sectionLayout,
			List<ItemClass> items) {
		super();
		this.itemLayout = itemLayout;
		this.items = items;
		this.sectionrLayout = sectionLayout;
		objects = new ArrayList<Object>();
		findSections();
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		int itemViewType = getItemViewType(position);
		int layout = getLayout(itemViewType);

		if (row == null) {
			row = inflate(parent, layout);
		}
		Object object = objects.get(position);

		switch (itemViewType) {
		case VIEW_TYPE_ITEM:
			return getItemView(position, row, parent, (ItemClass) object);
		case VIEW_TYPE_SECTION:
			return getHeaderView(position, row, parent, (SectionClass) object);
		}
		return row;
	}

	private int getLayout(int viewType) {
		switch (viewType) {
		case VIEW_TYPE_ITEM:
			return itemLayout;
		case VIEW_TYPE_SECTION:
			return sectionrLayout;
		}
		return -1;
	}

	private View inflate(ViewGroup parent, int layout) {
		View row;
		LayoutInflater inflater = getLayoutInflater(parent);
		row = inflater.inflate(layout, parent, false);
		return row;
	}

	private final void findSections() {
		sections = new ArrayList<SectionClass>();
		objects = new ArrayList<Object>();
		for (int i = 0; i < items.size(); i++) {
			ItemClass obj1 = items.get(i);
			SectionClass section = getSection(obj1);
			if (!sections.contains(section)) {
				sections.add(section);
				objects.add(section);
			}
			addObject(objects, obj1);
		}
	}

	private void addObject(List<Object> objects, Object object) {
		if (object == null) {
			return;
		}
		if (objects.contains(object)) {
			return;
		}
		objects.add(object);
	}

	/**
	 * Return SectionClass for this object.
	 */
	abstract protected SectionClass getSection(ItemClass obj1);

	abstract protected View getItemView(int position, View row,
			ViewGroup parent, ItemClass object);

	abstract protected View getHeaderView(int position, View row,
			ViewGroup parent, SectionClass object);

	@Override
	public int getViewTypeCount() {
		// Object + Section
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		Object object = objects.get(position);
		if (items.contains(object)) {
			return VIEW_TYPE_ITEM;
		} else if (sections.contains(object)) {
			return VIEW_TYPE_SECTION;
		}
		return IGNORE_ITEM_VIEW_TYPE;
	}

	protected LayoutInflater getLayoutInflater(ViewGroup parent) {
		return (LayoutInflater) parent.getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}

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
}
