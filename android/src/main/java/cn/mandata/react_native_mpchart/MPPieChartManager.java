package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Random;


public class MPPieChartManager extends MPPieRadarChartManager {
    private String CLASS_NAME="MPPieChart";

    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected PieChart createViewInstance(ThemedReactContext reactContext) {
        PieChart chart= new PieChart(reactContext);
        return chart;
    }

    @ReactProp(name = "holeRadius", defaultFloat = 50f)
    public void setHoleRadius(PieChart chart, float holeRadius){
        chart.setHoleRadius(holeRadius);
        chart.invalidate();
    }

    @ReactProp(name="drawSliceText", defaultBoolean = false)
    public void setDrawSliceText(PieChart chart, boolean enabled){
        chart.setDrawSliceText(enabled);
        chart.invalidate();
    }

    @ReactProp(name="usePercentValues", defaultBoolean = false)
    public void setUsePercentValues(PieChart chart, boolean enabled){
        chart.setUsePercentValues(enabled);
        chart.invalidate();
    }

    @ReactProp(name="centerText")
    public void setCenterText(PieChart chart, String v){
        chart.setCenterText(v);
        chart.invalidate();
    }

    @ReactProp(name = "centerTextRadiusPercent", defaultFloat = 1.f)
    public void setCenterTextRadiusPercent(PieChart chart, float percent){
        chart.setCenterTextRadiusPercent(percent);
        chart.invalidate();
    }

    @ReactProp(name="data")
    public void setData(PieChart chart,ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        ReadableArray ra=rm.getArray("yValues");
        
        for(int i=0;i<ra.size();i++){
            yVals1.add(new Entry((float)ra.getDouble(i) , i));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        
        chart.setDescription(""); 
        chart.setBackgroundColor(Color.argb(0, 0, 0, 0));
        chart.setData(data);
        chart.invalidate();
    }
}
