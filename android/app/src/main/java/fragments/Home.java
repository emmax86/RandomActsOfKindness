package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.dstny.activities.R;


public class Home extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }
}
