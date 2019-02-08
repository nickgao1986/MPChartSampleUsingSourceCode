
package com.babytree.apps.pregnancy.healthanalysis.widget.barchart;

import android.content.Context;

/**
 * Abstract baseclass of all Renderers.
 * 
 * @author Philipp Jahoda
 */
public abstract class Renderer {

    /**
     * the component that handles the drawing area of the chart and it's offsets
     */
    protected ViewPortHandler mViewPortHandler;
    protected Context mContext;

    public Renderer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
