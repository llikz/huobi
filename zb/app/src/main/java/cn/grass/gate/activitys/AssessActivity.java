package cn.grass.gate.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.widgets.TopView;

/**
 * Created by min on 2017/8/4.
 */

public class AssessActivity extends BaseActivity implements View.OnClickListener{
    private AssessActivity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess);
        activity = this;
        initView();
    }

    private void initView() {
        ((TopView) findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });

        BarChart mBarChart = (BarChart) findViewById(R.id.chart);
        setBarChartStyle(mBarChart);
        // 制作10个数据点。
        setData(mBarChart, 10);

    }

    public static void startAssessActivity(Context c) {
        Intent it = new Intent(c, AssessActivity.class);
        ((Activity) c).startActivity(it);
    }

    private void setBarChartStyle(BarChart mBarChart) {

        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        Description description = new Description();
        description.setText("这个是用来测试的barchart");
        mBarChart.setDescription(description);
        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setTextColor(Color.BLUE);

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setTextColor(Color.GREEN);

        Legend mLegend = mBarChart.getLegend();
        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.SQUARE);
        mLegend.setFormSize(15f);
        mLegend.setTextSize(12f);
        mLegend.setXEntrySpace(5f);
    }


    private void setData(BarChart mBarChart, int count) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(i, i + "");
        }

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * 100);
            yVals.add(new BarEntry(val, i));
        }

        BarDataSet mBarDataSet = new BarDataSet(yVals, "柱状图测试数据");

        // 如果是0f，那么柱状图之间将紧密无空隙的拼接在一起形成一片。
//        mBarDataSet.setBarSpacePercent(30f);

        // 柱状图柱的颜色
        mBarDataSet.setColor(Color.RED);

        // 当柱状图某一柱被选中时候的颜色
        mBarDataSet.setHighLightColor(Color.YELLOW);

//        mBarDataSet.setValueFormatter(new ValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value) {
//                DecimalFormat decimalFormat = new DecimalFormat(".0");
//                String s = decimalFormat.format(value) + "毫米";
//
//                return s;
//            }
//        });

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(mBarDataSet);

//        BarData mBarData = new BarData(xVals, dataSets);
        BarData mBarData = new BarData(mBarDataSet);
        mBarData.setValueTextSize(12f);

        mBarChart.setData(mBarData);
    }

    @Override
    public void onClick(View v) {

    }
}
