
package nickgao.com.viewpagerswitchexample;

import android.os.Bundle;
import android.view.WindowManager;

import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LineChart;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LoveLineChart;

import nickgao.com.viewpagerswitchexample.data.DemoBase;


public class LineChartActivity3 extends DemoBase {

    private LineChart chart;
    public static float mXaxisLabelCount = 8;
    public static final int XAXIS_LABEL_COUNT_TOTAL = 60;
    public static final long DAY = 24 * 60 * 60 * 1000;
    public static final int MAX_VISIBLE_VALUE_COUNT = 39;
    private static String[] colors = {"#BBBBBB", "#AA99ED", "#F37997", "#FFBB86", "#49C9C9", "#FFD432","#FF927D","#AA99ED","#79D2FF"};
    public static final float XAXIS_LABEL_COUNT = 8f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_love_linechart);

        setTitle("LineChartActivity1");

        // // Chart Style // //
        LoveLineChart chart = (LoveLineChart) findViewById(R.id.chart1);
        LoveLineChartHelper.initLineChart(this,chart,XAXIS_LABEL_COUNT);
    }


    @Override
    protected void saveToGallery() {

    }
}
