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
        xVals.add("x1");
        xVals.add("x2");
        xVals.add("x3");
        xVals.add("x4");

        ArrayList<BarEntry> vals = new ArrayList<BarEntry>();
        vals.add(new BarEntry(5,0));
        vals.add(new BarEntry(3,0));
        vals.add(new BarEntry(6,0));
        vals.add(new BarEntry(7,0));

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(new BarDataSet(vals,xVals.get(0)));

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);
        //https://github.com/PhilJay/MPAndroidChart/wiki/Setting-Data

        chart.invalidate();

        return view;
    }


}
