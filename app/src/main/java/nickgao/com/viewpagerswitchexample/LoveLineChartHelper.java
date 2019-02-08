package nickgao.com.viewpagerswitchexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.AxisBase;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.Entry;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.IFillFormatter;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.ILineDataSet;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LineData;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LineDataProvider;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LineDataSet;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.LoveLineChart;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.Utils;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.ValueFormatter;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.XAxis;
import com.babytree.apps.pregnancy.healthanalysis.widget.barchart.YAxis;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by wlf on 2019/01/22
 */
public class LoveLineChartHelper  {
    public static  float  mXaxisLabelCount = 8;
    public static final int XAXIS_LABEL_COUNT_TOTAL = 60;
    private static String[] colors = {"#ffbb86", "#F37997", "#ff927d", "#AA99ED", "#79D2FF", "#49C9C9","#BBBBBB"};
    private static float moveX ;
    public static final long DAY = 24 * 60 * 60 * 1000;

    public static void initLineChart(Activity context, LoveLineChart chart, float xaxisLabelCount ) {
        try {
            if(chart!=null) {
                mXaxisLabelCount = xaxisLabelCount;
                ArrayList<Entry> values = new ArrayList<>();
                ArrayList<Entry> valuesLove = new ArrayList<>();
                {   // // Chart Style // //
                    // background color
                    chart.setBackgroundColor(Color.WHITE);
                    // disable description text
                    chart.getDescription().setEnabled(false);
                    // enable touch gestures
                    chart.setTouchEnabled(true);
                    // set listeners

                    chart.setDrawGridBackground(false);
                    // enable scaling and dragging
                    chart.setDragEnabled(true);
                    chart.setScaleEnabled(true);
                    // chart.setScaleXEnabled(true);
                    // chart.setScaleYEnabled(true);
                    // force pinch zoom along both axis
                    chart.setPinchZoom(true);
                    final ArrayList<String> alxLabel = new ArrayList<>();
                    XAxis xAxis;
                    {   // // X-Axis Style // //
                        xAxis = chart.getXAxis();
                        xAxis.setYOffset(6f);
                        xAxis.setXOffset(30f);
                        // vertical grid lines
                        xAxis.setDrawGridLines(true);
                        xAxis.enableAxisLineDashedLine(10f, 0, 0);
                        xAxis.setGridColor(Color.parseColor("#F5F5F5"));
                        xAxis.setGridLineWidth(1);
                        xAxis.setTextColor(Color.parseColor("#FF666666"));
                        xAxis.setDrawAxisLine(true);
                        xAxis.setAxisLineColor(Color.parseColor("#DDDDDD"));
                        xAxis.setAxisLineWidth(1f);
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                        xAxis.setLabelCount((int)mXaxisLabelCount, false);
                        xAxis.setTextSize(10);
                        xAxis.setTextColor(Color.parseColor("#FF666666"));
                        xAxis.setAxisMinimum(0f);
                        xAxis.setAxisMaximum(XAXIS_LABEL_COUNT_TOTAL+0.1f);

                        alxLabel.add("");
                        final long curTime = System.currentTimeMillis();

                        for (int i = XAXIS_LABEL_COUNT_TOTAL - 1; i >= 0; i--) {
                            boolean isVisibleOvulation =false;
                            boolean isPregnancy = true;
                            long startTime;
                            if(!isPregnancy) {
                                startTime = (curTime + 7 * DAY - DAY * i);
                            }else{
                                startTime = curTime - DAY * i;
                            }
                            String startTimeMMdd;
                            if(startTime==curTime) {
                                startTimeMMdd = "今天";
                            }else{
                                startTimeMMdd = CalendarUtil.formatMMdd(startTime);
                            }
                           // String startTimeYYMMdd = CalendarUtil.format(startTime);
                            alxLabel.add(startTimeMMdd);
                            Random rand = new Random();
                            float percentage = rand.nextFloat()*40;
                            if (percentage > 40f) {
                                percentage = 40;
                            }

                            int type = rand.nextInt(6);//0 安全期  ,1月经期 ，2孕早期 ，3孕中期 ，4 孕晚期，5易孕期，6未知期
                            int fillDrawble = R.drawable.ca_gradient_bbbbbb;
                            if(type == 0) {
                                fillDrawble = R.drawable.ca_gradient_bbbbbb;
                            }else if(type == 1) {
                                fillDrawble = R.drawable.ca_gradient_49c9c9;
                            }else if(type == 2) {
                                fillDrawble = R.drawable.ca_gradient_79d2ff;
                            }else if(type == 3) {
                                fillDrawble = R.drawable.ca_gradient_aa99ed;
                            }
                            //fillDrawble  = R.drawable.ca_gradient_bbbbbb;
                            Entry entry = new Entry(XAXIS_LABEL_COUNT_TOTAL - i, percentage, context.getResources().getDrawable(R.drawable.ca_label_makelove), Color.parseColor(colors[type]), true ,false,fillDrawble);
                            entry.realValue = percentage;
                            values.add(entry);

//                            Entry entry1 = new Entry(XAXIS_LABEL_COUNT_TOTAL - i, 5f, context.getResources().getDrawable(R.drawable.ca_img_calender_label_ovulation), Color.parseColor(colors[6]), false,isVisibleOvulation,fillDrawble);
//                            entry1.realValue = 0;
//                            valuesLove.add(entry1);
                        }

                        //X轴自定义值
                        xAxis.setValueFormatter(new ValueFormatter() {
                            @Override
                            public String getAxisLabel(float value, AxisBase axis, int i) {
                                int x = (int) value;
                                if (alxLabel.size() > x) {
                                    return alxLabel.get((int) (value));
                                } else {
                                    return  CalendarUtil.formatMMdd(curTime);
                                }
                            }
                        });

                    }

                    YAxis yAxis;
                    {   // // Y-Axis Style // //
                        yAxis = chart.getAxisLeft();

                        // disable dual axis (only use LEFT axis)
                        chart.getAxisRight().setEnabled(false);
                        yAxis.disableAxisLineDashedLine();
                        yAxis.setDrawGridLines(false);
                        yAxis.setDrawAxisLine(true);
                        yAxis.setAxisLineColor(Color.parseColor("#DDDDDD"));
                        yAxis.setTextColor(Color.parseColor("#FF666666"));
                        yAxis.setAxisLineWidth(1);
                        yAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
                        yAxis.setAxisMaximum(45f);
                        // horizontal grid lines
                        yAxis.setLabelCount(5, false);
                        // axis range
                        yAxis.setAxisMinimum(-3f);
                        yAxis.setTextSize(11);
                        //Y轴自定义值
                        yAxis.setValueFormatter(new ValueFormatter() {
                            @Override
                            public String getAxisLabel(float value, AxisBase axis, int i) {
                                int x = (int) value;
                                return x+"%";

                            }
                        });
                    }
                    // add data
                    setData(context, chart, values,valuesLove);
                    float scale = (XAXIS_LABEL_COUNT_TOTAL + 1) / mXaxisLabelCount;
                    Matrix m = new Matrix();
                    m.postScale(scale, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的3倍
                    chart.getViewPortHandler().refresh(m, chart, false);//将图表动画显示之前进行缩放
                    chart.setScaleYEnabled(false);
                    chart.setScaleXEnabled(false);
                    // draw points over time
                    //        chart.animateX(0);
                    if (alxLabel.size() > 9) {
                        moveX = alxLabel.size() - 8;
                    }
                    chart.moveViewToX(moveX);
                    //        chart.invalidate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void setData(final Context context, final LoveLineChart chart, ArrayList values , ArrayList valuesLove) {
        LineDataSet set1;
        LineDataSet set2;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(1);
            if(set1!=null&&set2!=null) {
                set1.setValues(values);
                set2.setValues(valuesLove);
                set1.notifyDataSetChanged();
                set2.notifyDataSetChanged();
            }
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setDrawIcons(false);
            // draw dashed line
            set1.enableDashedLine(30f, 15f, 5f);
            // black lines and points
            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.GREEN);
            // line thickness and point size
            set1.setLineWidth(1.5f);
            set1.setCircleRadius(4f);
            set1.setCircleHoleRadius(2.5F);
            // draw points as solid circles
            set1.setDrawCircleHole(true);
            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            // text size of values
            set1.setValueTextSize(9f);
            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setHighlightEnabled(false);
            // set the filled area
            set1.setDrawFilled(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            set2 = new LineDataSet(valuesLove, "DataSet2");
            set1.setCubicIntensity(0.01f);
            set2.setCubicIntensity(0.01f);
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set2.setDrawIcons(false);
            // draw dashed line
            set2.enableDashedLine(30f, 15f, 5f);
            // black lines and points
            set2.setColor(Color.BLUE);
            set2.setCircleColor(Color.GREEN);
            // line thickness and point size
            set2.setLineWidth(1.5f);
            set2.setCircleRadius(4f);
            set2.setCircleHoleRadius(2.5F);
            // draw points as solid circles
            set2.setDrawCircleHole(true);
            // customize legend entry
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);
            // text size of values
            set2.setValueTextSize(9f);
            // draw selection line as dashed
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setHighlightEnabled(false);
            // set the filled area
            set2.setDrawFilled(false);
            set2.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ca_fade_red);
                set1.setFillDrawable(drawable);
                set2.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
                set2.setFillColor(Color.BLACK);
            }
            set2.setDrawCircles(false);
            set2.setDrawValues(true);
            set1.isDrawLine = true;
            set2.isDrawLine = false;
            set1.isDrawValues = true;
            set2.isDrawValues = false;
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets
            dataSets.add(set2); // add the data sets
            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            // set data
            chart.setData(data);
        }
    }
}
