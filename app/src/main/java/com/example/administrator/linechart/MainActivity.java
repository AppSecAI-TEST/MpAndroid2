package com.example.administrator.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    public List<String> allSaoMaTimeList;//所有的扫码时间
    public List<String> allSaoMaTimeList2;//截取整点的扫码时间 //X轴
    public List<String> allSaoMaList;//扫码率
    public List<String> allSaoMaY;//Y轴
    @InjectView(R.id.chart1)
    LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        queryAllSaoMa0();
    }

    //获取扫码成功率的折线图
    public void queryAllSaoMa0() {
        allSaoMaTimeList = new ArrayList<String>();//折线图X轴时间集合
        allSaoMaTimeList2 = new ArrayList<String>();//折线图X轴时间集合筛选整点
        allSaoMaList = new ArrayList<String>();//折线图数据坐标集合
        allSaoMaList.add(90 + "");
        allSaoMaList.add(50 + "");
        allSaoMaList.add(80 + "");
        allSaoMaList.add(49 + "");
        allSaoMaList.add(73 + "");
        allSaoMaList.add(91 + "");
        allSaoMaList.add(72 + "");
        allSaoMaList.add(85 + "");
        allSaoMaList.add(72 + "");

        allSaoMaTimeList2.add("4:00");
        allSaoMaTimeList2.add("5:00");
        allSaoMaTimeList2.add("6:00");
        allSaoMaTimeList2.add("7:00");
        allSaoMaTimeList2.add("8:00");
        allSaoMaTimeList2.add("9:00");
        allSaoMaTimeList2.add("10:00");
        allSaoMaTimeList2.add("11:00");
        allSaoMaTimeList2.add("12:00");


        LineData mLineData = getLineData(allSaoMaTimeList2.size(), 100);
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));
    }

    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
         //  lineChart.setDrawBorders(true);  //是否在折线图上添加边框/************************************/
        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");
        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        //  lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度/************************/

        // enable touch gestures
        lineChart.setTouchEnabled(false); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextSize(36f);//标题字体大小
        mLegend.setTextColor(Color.WHITE);// 颜色
        mLegend.setXOffset(50);
        mLegend.setYOffset(20);
        //  mLegend.setTypeface(mTf);// 字体
        lineChart.getAxisRight().setEnabled(true); // 隐藏右边 的坐标轴
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // 让x轴在下面

       //888888888888888888888888888888888888888888888888888888888888//
        LimitLine ll1 = new LimitLine(100f, "上限");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(40f, "下限");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll2.setTextSize(10f);
        YAxis leftAxis = lineChart.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //设置优秀线
        leftAxis.addLimitLine(ll1);
        //设置及格线
        leftAxis.addLimitLine(ll2);
        //y轴最大
        leftAxis.setAxisMaxValue(110f);
//        leftAxis.setAxisMaximum(200f);
        //y轴最小
        leftAxis.setAxisMinValue(0f);
      //  leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
      //  leftAxis.setDrawZeroLine(false);

        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(false);
//888888888888888888888888888888888888888888888888888888888888//

//        lineChart.getXAxis().setGridColor(
//                getResources().getColor(R.color.Red_800));//x轴颜色
//        lineChart.getAxisLeft().setGridColor(
//                getResources().getColor(R.color.orange));//x轴颜色
        lineChart.getAxisRight().setGridColor(
                getResources().getColor(R.color.orange));//x轴颜色

        lineChart.getXAxis().setTextSize(15f);//x轴字体大小
        lineChart.getAxisLeft().setTextSize(15f);//Y轴字体大小
        lineChart.getAxisRight().setTextSize(15f);//Y轴字体大小
        lineChart.animateX(6000); // 立即执行的动画,x轴
    }

    /**
     * 生成一个数据
     *
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, int range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(allSaoMaTimeList2.get(i));
        }
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = Float.parseFloat(allSaoMaList.get(i));
            yValues.add(new Entry(value, i));
        }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "扫码成功率折线图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数1
        lineDataSet.setDrawCubic(true);  //设置曲线为圆滑的线
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setValueTextSize(13f);
        lineDataSet.setCircleSize(5f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色1
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // 添加数据集合1

        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }
}
