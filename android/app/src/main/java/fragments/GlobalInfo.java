package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import me.dstny.activities.R;

public class GlobalInfo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_globalinfo, container, false);

        BarChart chart = (BarChart) view.findViewById(R.id.chart);

        // Get Global Data


        // Add to the chart
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("GLOBAL STATS");
        xVals.add("GLOBAL ST2");
        xVals.add("GLOBAL ST3");
        xVals.add("GLOBAL ST4");

        ArrayList<BarEntry> vals1 = new ArrayList<BarEntry>();
        vals1.add(new BarEntry(5,0));
        ArrayList<BarEntry> vals2 = new ArrayList<BarEntry>();
        vals2.add(new BarEntry(3,0));
        ArrayList<BarEntry> vals3 = new ArrayList<BarEntry>();
        vals3.add(new BarEntry(6,0));
        ArrayList<BarEntry> vals4 = new ArrayList<BarEntry>();
        vals4.add(new BarEntry(7,0));

        ArrayList<BarDataSet> dataSets1 = new ArrayList<BarDataSet>();
        dataSets1.add(new BarDataSet(vals1,"SOMETHING"));
        ArrayList<BarDataSet> dataSets2 = new ArrayList<BarDataSet>();
        dataSets2.add(new BarDataSet(vals2,"SOEMTHING2"));
        ArrayList<BarDataSet> dataSets3 = new ArrayList<BarDataSet>();
        dataSets3.add(new BarDataSet(vals3,"SOEMTHING3"));
        ArrayList<BarDataSet> dataSets4 = new ArrayList<BarDataSet>();
        dataSets4.add(new BarDataSet(vals4,"SOEMTHING4"));

        BarData data1 = new BarData(xVals, dataSets1);
        BarData data2 = new BarData(xVals, dataSets2);
        BarData data3 = new BarData(xVals, dataSets3);
        BarData data4 = new BarData(xVals, dataSets4);

        chart.setData(data1);
        chart.setData(data2);
        chart.setData(data3);
        chart.setData(data4);
        //https://github.com/PhilJay/MPAndroidChart/wiki/Setting-Data

        chart.invalidate();

        return view;
    }


}
