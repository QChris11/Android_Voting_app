package com.example.vote_app;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinishVote extends AppCompatActivity {

    private FloatingActionButton fab, fab1, fab2, fab3;
    private boolean isOpen = false;
    private BarChart barChart;
    private PieChart pieChart;
    private LineChart lineChart;

    private String[] types = {"银行", "非银金融", "建筑材料", "化工", "医药生物", "电子"};
    private float[] changes = {27.91f, 5.9f, -0.4f, -17.79f, -21.85f, -39.58f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_vote);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setButtonClickListener();

        initBarChart(barChart);
        setBarChartData(types.length, barChart);

        initPieChart(pieChart);
    }

    private void setButtonClickListener(){

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);

        barChart = findViewById(R.id.bar_chart);
        lineChart = findViewById(R.id.line_chart);
        pieChart = findViewById(R.id.pie_chart);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if(isOpen){
                    fab1.setVisibility(View.GONE);
                    fab2.setVisibility(View.GONE);
                    fab3.setVisibility(View.GONE);
                    isOpen = false;
                } else {
                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
                pieChart.setVisibility(View.GONE);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.setVisibility(View.GONE);
                barChart.setVisibility(View.GONE);
                pieChart.setVisibility(View.VISIBLE);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.GONE);
            }
        });
    }

    private void initBarChart(BarChart mBarChart) {
        mBarChart.setBackgroundColor(Color.WHITE);
        mBarChart.setDrawGridBackground(false); //网格
        mBarChart.getDescription().setEnabled(false);//描述
        //背景阴影
        mBarChart.setDrawBarShadow(true);

        //显示边界
        mBarChart.setDrawBorders(false);

        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);

        //折线图例 标签 设置
        Legend l = mBarChart.getLegend();
        l.setEnabled(false);

        YAxis leftAxis = mBarChart.getAxisLeft();
        YAxis rightAxis = mBarChart.getAxisRight();
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);

        XAxis xAxis = mBarChart.getXAxis();

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setGranularity(1f);

        //xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        xAxis.setTextColor(0xff74828F);
        xAxis.setTextSize(10f);
        xAxis.setAxisLineColor(0xffe0e0e0);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int idx = (int) value;
                return types[idx];
            }
        });
    }

    private void setBarChartData(int count, BarChart mChart) {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        int[] colors = new int[count];

        for (int i = 0; i < count; i++) {
            float val = changes[i];

            if (val > 0) {
                colors[i] = 0xffF04933;
            }

            if (val < 0) {
                colors[i] = 0xff2BBE53;
            }

            yVals.add(new BarEntry(i, Math.abs(val)));
        }

        BarDataSet mBarDataSet = new BarDataSet(yVals, "股票数据");
        mBarDataSet.setDrawIcons(false);
        mBarDataSet.setColors(colors);
        mBarDataSet.setValueTextSize(12f);
        mBarDataSet.setValueTextColor(0xff74828F);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(mBarDataSet);

        BarData mBarData = new BarData(dataSets);
        mBarData.setBarWidth(0.6f);

        mBarData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int idx = (int) entry.getX();
                return String.valueOf(changes[idx]);
            }
        });

        mChart.setData(mBarData);
    }

    private void initPieChart(PieChart mPieChart){
        mPieChart.setUsePercentValues(true);

        mPieChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
//        mPieChart.setBackgroundColor(R.color.pieChartBg);      //设置pieChart图表背景色
        mPieChart.setRotationEnabled(true);//可以手动旋转
        mPieChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
        mPieChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果

//        mPieChart.setHoleColor(R.color.pieChartBg);

//        Legend l = mPieChart.getLegend();
//        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setForm(Legend.LegendForm.DEFAULT); //设置图例的形状
//        l.setFormSize(10);                      //设置图例的大小
//        l.setFormToTextSpace(10f);              //设置每个图例实体中标签和形状之间的间距
//        l.setDrawInside(false);
//        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
//        l.setXEntrySpace(10f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
//        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
//        l.setYOffset(0f);                      //设置比例块Y轴偏移量
//        l.setTextSize(14f);                      //设置图例标签文本的大小
//        l.setTextColor(Color.parseColor("#333333"));//设置图例标签文本的颜色

        // pieChart 添加数据
        setData(types.length, mPieChart);

    }

    private void setData(int count, PieChart mPieChart) {
        ArrayList<PieEntry> yVals = new ArrayList<>();

        ArrayList<Integer> colors = new ArrayList();//颜色列表

        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            colors.add(Color.rgb(r,g,b));

            yVals.add(new PieEntry(Math.abs(changes[i]), (Object)types[i]));
        }
        //饼状图数据集
        PieDataSet pieDataSet = new PieDataSet(yVals, "股票数据");
        pieDataSet.setSliceSpace(3f);      //设置饼状之间的间隙
        pieDataSet.setSelectionShift(10f); //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);      //为DataSet中的数据匹配上颜色集
        // pieChart 最终数据 PieData
        PieData pieData = new PieData(pieDataSet);
//        pieData.setValueFormatter(new PercentFormatter());  //为Item上的数据添加一个Formatter
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);     // 设置百分比字体颜色

        pieData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                int idx = (int) entry.getX();
                return String.valueOf(value).substring(0,5) + "%" + "\n" + entry.getData();
            }
        });

        mPieChart.setData(pieData);
//        mPieChart.highlightValues(null);
        mPieChart.invalidate();    // 将图表重绘以显示设置的属性和数据
    }


//    private void initLineChart(LineChart lineChart) {
//        /***图表设置***/
//        //是否展示网格线
//        lineChart.setDrawGridBackground(false);
//        //是否显示边界
//        lineChart.setDrawBorders(true);
//        //是否可以拖动
//        lineChart.setDragEnabled(false);
//        //是否有触摸事件
//        lineChart.setTouchEnabled(true);
//        //设置XY轴动画效果
//        lineChart.animateY(2500);
//        lineChart.animateX(1500);
//
//        /***XY轴的设置***/
//        XAxis xAxis = lineChart.getXAxis();
//        YAxis leftYAxis = lineChart.getAxisLeft();
//        //X轴设置显示位置在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMinimum(0f);
//        xAxis.setGranularity(1f);
//        //保证Y轴从0开始，不然会上移一点
//        leftYAxis.setAxisMinimum(0f);
//
//        /***折线图例 标签 设置***/
//        Legend legend = lineChart.getLegend();
//        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
//        legend.setForm(Legend.LegendForm.LINE);
//        legend.setTextSize(12f);
//        //显示位置 左下方
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        //是否绘制在图表里面
//        legend.setDrawInside(false);
//    }
//
//
//    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
//        lineDataSet.setColor(color);
//        lineDataSet.setCircleColor(color);
//        lineDataSet.setLineWidth(1f);
//        lineDataSet.setCircleRadius(3f);
//        //设置曲线值的圆点是实心还是空心
//        lineDataSet.setDrawCircleHole(false);
//        lineDataSet.setValueTextSize(10f);
//        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
//        lineDataSet.setFormLineWidth(1f);
//        lineDataSet.setFormSize(15.f);
//        if (mode == null) {
//            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        } else {
//            lineDataSet.setMode(mode);
//        }
//    }
//
//    public void showLineChart(float[] dataList, String name, int color) {
//        List<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < dataList.length; i++) {
//            float data = dataList[i];
//            /**
//             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
//             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
//             */
//            Entry entry = new Entry(i, data);
//            entries.add(entry);
//        }
//        // 每一个LineDataSet代表一条线
//        LineDataSet lineDataSet = new LineDataSet(entries, name);
//        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
//        LineData lineData = new LineData(lineDataSet);
//        lineChart.setData(lineData);
//
//    }


}
