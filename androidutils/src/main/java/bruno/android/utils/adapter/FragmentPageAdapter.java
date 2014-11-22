package bruno.android.utils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

	List<String> titles = new ArrayList<String>();
	List<Fragment> fragments = new ArrayList<Fragment>();

	public FragmentPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		return fragments.get(index);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int index) {
		return titles.get(index);

	}

	public void addFragment(Fragment fragment, String title, int index) {
		if (fragments.contains(fragment)) {
			return;
		}
		fragments.add(index, fragment);
		titles.add(index, title);
	}

	public void addFragment(Fragment fragment, String title) {
		if (fragments.contains(fragment)) {
			return;
		}
		fragments.add(fragment);
		titles.add(title);
	}

	public void removeFragment(Fragment fragment) {
		int index = fragments.indexOf(fragment);
		removeFragment(index);
	}

	public void removeFragment(String title) {
		int index = titles.indexOf(title);
		removeFragment(index);
	}

	public void removeFragment(int index) {
		try {
			titles.remove(index);
			fragments.remove(index);
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Override
	public int getItemPosition(Object object) {
		if (fragments.contains(object)) {
			return super.getItemPosition(object);
		}
		return POSITION_NONE;
	}

}
