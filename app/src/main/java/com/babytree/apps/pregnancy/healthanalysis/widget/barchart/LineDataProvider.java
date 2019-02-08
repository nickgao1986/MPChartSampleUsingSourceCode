package com.babytree.apps.pregnancy.healthanalysis.widget.barchart;


public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
