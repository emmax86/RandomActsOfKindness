package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import me.dstny.activities.R;

public class GlobalInfo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_globalinfo, container, false);

        BarChart chart = (BarChart) view.findViewById(R.id.chart);

        // Get Global Data
        // Add to the chart

        chart.invalidate();

        return view;
    }


}
