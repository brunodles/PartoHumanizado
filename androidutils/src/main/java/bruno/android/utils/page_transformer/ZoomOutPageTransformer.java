package bruno.android.utils.page_transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ZoomOutPageTransformer implements PageTransformer {

	private static float MIN_SCALE = 0.80f;
	private static float MIN_ALPHA = 0.9f;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();

		float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
		if (position < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
			// view.setAlpha(0);

		} else if (position <= 1) { // [-1,1]
			// Modify the default slide transition to shrink the page as well
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
			if (position < 0) {
				view.setTranslationX(horzMargin - vertMargin / 2);
			} else {
				view.setTranslationX(-horzMargin + vertMargin / 2);
			}

			// Scale the page down (between MIN_SCALE and 1)
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);

			// Fade the page relative to its size.

		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
			// view.setAlpha(0);
		}
		view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)
				* (1 - MIN_ALPHA));
	}
}