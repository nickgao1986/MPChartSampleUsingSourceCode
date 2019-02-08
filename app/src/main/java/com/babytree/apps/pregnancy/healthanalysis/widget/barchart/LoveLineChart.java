
package com.babytree.apps.pregnancy.healthanalysis.widget.barchart;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Chart that draws lines, surfaces, circles, ...
 *
 * @author Philipp Jahoda
 */
public class LoveLineChart  extends BarLineChartBase<LineData> implements LineDataProvider {

    public LoveLineChart(Context context) {
        super(context);
    }

    public LoveLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoveLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        type = LOVE_CHART;
        offsetRight = 25;
        offsetTop = 0;
        super.init();
        mLegendRenderer = null;
        mRenderer = new LoveLineChartRenderer(this, mAnimator, mViewPortHandler);
    }
    @Override
    public LineData getLineData() {
        return mData;
    }

    @Override
    protected void onDetachedFromWindow() {
        // releases the bitmap in the renderer to avoid oom error
        if (mRenderer != null && mRenderer instanceof LineChartRenderer) {
            ((LineChartRenderer) mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
