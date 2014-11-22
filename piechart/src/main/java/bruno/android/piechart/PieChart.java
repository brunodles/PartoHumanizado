package bruno.android.piechart;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by dev on 24/09/2014.
 */
public class PieChart extends View {

    public static final int ANGLE_FIX = 360;
    public static final float ITEM_PERCENTAGE_RADIUS = .27f;
    public static final float TEXT_RADIUS = .45f;
    public static final float ITEM_TIP_RADIUS = .38f;
    public static final float ITEM_TIP_BASE_RADIUS = .35f;
    public static final int ITEM_TIP_BASE_SIZE = 3;

    private Paint piePaint;
    private HashMap<String, Item> mData = new HashMap<String, Item>();
    private float total;
    private Paint textPaint;

    private float rotation = 0;
    private RectF bounds;
    private RectF arcBounds;
    private float chartPercentage = .85f;
    private int textHeightInSp = 20;

    public PieChart(Context context) {
        super(context);
        init();
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                textHeightInSp, getResources().getDisplayMetrics());

        // Set up the paint for the pie slices
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textSize);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            piePaint.setColor(Color.GRAY);
            canvas.drawOval(arcBounds, piePaint);
            return;
        }

        Collection<Item> values = mData.values();
        for (Item it : values) {
            if (!it.validItem())
                continue;
            drawArc(canvas, it);
            piePaint.setColor(Color.GRAY);
//            drawItemPct(canvas, it);
            if (it.text != null && it.text.length() > 0)
                drawItemText(canvas, it, it.text, TEXT_RADIUS, piePaint);
            if (it.icon != null)
                drawItemIcon(canvas, it, TEXT_RADIUS, piePaint);
        }
        for (Item it : values) {
            if (!it.validItem())
                continue;
//            drawArc(canvas, it);
//            piePaint.setColor(Color.GRAY);
            drawItemPct(canvas, it);
//            if (it.text != null && it.text.length() > 0)
//                drawItemText(canvas, it, it.text, .45f, piePaint);
//            if (it.icon != null)
//                drawItemIcon(canvas, it, .45f, piePaint);
        }
    }

    private void drawArc(Canvas canvas, Item it) {
//            piePaint.setShader(it.mShader);
        piePaint.setColor(it.color);
        canvas.drawArc(arcBounds,
                rotation + ANGLE_FIX - it.endAngle,
                it.endAngle - it.startAngle,
                true, piePaint);

        float centerAngle = ANGLE_FIX - rotation + (it.endAngle + it.startAngle) / 2;
        float[] tip = getCoordinates(centerAngle, ITEM_TIP_RADIUS);
        float[] b1 = getCoordinates(centerAngle + ITEM_TIP_BASE_SIZE, ITEM_TIP_BASE_RADIUS);
        float[] b2 = getCoordinates(centerAngle - ITEM_TIP_BASE_SIZE, ITEM_TIP_BASE_RADIUS);

        Path path = new Path();
        path.moveTo(tip[0], tip[1]);
        path.lineTo(b1[0], b1[1]);
        path.lineTo(b2[0], b2[1]);
        path.lineTo(tip[0], tip[1]);
        path.close();
        canvas.drawPath(path, piePaint);
    }

    private float[] getCoordinates(double angleCenter, float radius) {
        double currentXAngle = Math.cos(Math.toRadians(angleCenter));
        double currentYAngle = Math.sin(Math.toRadians(angleCenter));

        float currentXCoordinate = (float) (bounds.centerX() + ((bounds.width() * radius) * currentXAngle));
        float currentYCoordinate = (float) (bounds.centerY() - ((bounds.height() * radius) * currentYAngle));

        return new float[]{currentXCoordinate, currentYCoordinate};
    }

    private void drawItemPct(Canvas canvas, Item it) {
        String text = String.format("%02d%%", (int) toPct(it.value));
        textPaint.setColor(it.textColor);
        drawItemText(canvas, it, text, ITEM_PERCENTAGE_RADIUS, textPaint);
    }

    private float toPct(float value) {
        if (total <= 0)
            return 0;
        return value * 100 / total;
    }

    private void drawItemText(Canvas canvas, Item it, String text, float radius, Paint paint) {
        float widthStr = paint.measureText(text);

        double angleCenter = ANGLE_FIX - rotation + (it.startAngle + it.endAngle) / 2;

        float[] coordinates = getCoordinates(angleCenter, radius);

        canvas.drawText(
                text,
                (coordinates[0] - widthStr / 2),
                (coordinates[1] + paint.getTextSize() / 2),
                paint);
    }

    private void drawItemIcon(Canvas canvas, Item it, float radius, Paint paint) {

        double angleCenter = ANGLE_FIX - rotation + (it.startAngle + it.endAngle) / 2;

        float[] coordinates = getCoordinates(angleCenter, radius);
        canvas.drawBitmap(it.icon,
                (coordinates[0] - it.icon.getWidth() / 2),
                (coordinates[1] - it.icon.getHeight() / 2),
                paint);

    }

    private void drawPint(Canvas canvas, float[] coordinates, int size) {
        canvas.drawRect(
                coordinates[0] - size,
                coordinates[1] - size,
                coordinates[0] + size,
                coordinates[1] + size,
                piePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bounds = new RectF(0, 0, w, h);
        float right = w * chartPercentage;
        float bottom = h * chartPercentage;
        float left = ((bounds.centerX()) - right / 2) * 2;
        float top = ((bounds.centerY()) - bottom / 2) * 2;
        arcBounds = new RectF(left, top, right, bottom);
    }

    public class Item {
        private final String key;

        private String text;
        private Bitmap icon;
        private float value;

        private int color;
        private int textColor;

        // computed values
        private float startAngle;
        private float endAngle;

        private Item(String key) {
            this.key = key;
        }

        public Item setText(@StringRes int stringResId) {
            text = getResources().getString(stringResId);
            return this;
        }

        public Item setText(String text) {
            this.text = text;
            return this;
        }

        public Item setIcon(@DrawableRes int drawableResId) {
            this.icon = BitmapFactory.decodeResource(getResources(), drawableResId);
            return this;
        }

//        public Item setIcon(Drawable icon) {
//            this.icon = icon;
//            return this;
//        }

        private boolean validItem() {
            return value > 0;
        }
    }

    public Item addItem(String key, @ColorRes int color, @ColorRes int textColor) {
        Resources res = getResources();

        Item it = new Item(key);
        it.color = res.getColor(color);
        it.textColor = res.getColor(textColor);

        mData.put(key, it);

        return it;
    }

    public synchronized void setItemValue(String key, float value) {
        mData.get(key).value = value;
        calcTotal();
        calcAngle();
        postInvalidate();
    }

    private void calcTotal() {
        total = 0;
        for (Item item : mData.values())
            total += item.value;
    }

    private void calcAngle() {
        // When the data changes, we have to recalculate
        // all of the angles.
        float currentAngle = 0;
        for (Item it : mData.values()) {
            it.startAngle = currentAngle;
            it.endAngle = currentAngle + (it.value * 360.0f / total);
            currentAngle = it.endAngle;
        }
    }

    public float getTotal() {
        return total;
    }

    public void setRotationAngle(float rotation) {
        this.rotation = rotation;
        postInvalidate();
    }
}
