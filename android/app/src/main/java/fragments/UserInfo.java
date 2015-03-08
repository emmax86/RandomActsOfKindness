package fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import activities.MainActivity;
import holo.Bar;
import holo.BarGraph;
import me.dstny.activities.R;

public class UserInfo  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_userinfo, container, false);


        // Get Global Data


        int CALLS_VALUE = 5;
        int POSTS_VALUE = 3;
        int DONATIONS_VALUE = 7;
        int MAIL_VALUE = 6;
        // Add to the chart
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#F2385A"));
        d.setName("Calls");
        d.setValue(CALLS_VALUE);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#F5A503"));
        d2.setName("Posts");
        d2.setValue(POSTS_VALUE);
        points.add(d);
        points.add(d2);
        d2.setColor(Color.parseColor("#56D9CD"));
        d2.setName("Donations");
        d2.setValue(DONATIONS_VALUE);
        points.add(d);
        points.add(d2);
        d2.setColor(Color.parseColor("#3AA1BF"));
        d2.setName("Mail");
        d2.setValue(MAIL_VALUE);
        points.add(d);
        points.add(d2);

        BarGraph g = (BarGraph)view.findViewById(R.id.graph);
        g.setBars(points);


        return view;
    }
}