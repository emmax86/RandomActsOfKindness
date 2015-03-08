package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import activities.MainActivity;
import me.dstny.activities.R;
public class Home extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button actButton = (Button) view.findViewById(R.id.kindness_button);
        Button callButton = (Button) view.findViewById(R.id.call_button);
        Button postButton = (Button) view.findViewById(R.id.post_button);
        Button donateButton = (Button) view.findViewById(R.id.donate_button);
        Button mailButton = (Button) view.findViewById(R.id.mail_button);
        TextView callText = (TextView) view.findViewById(R.id.call_count);
        TextView postText = (TextView) view.findViewById(R.id.post_count);
        TextView donateText = (TextView) view.findViewById(R.id.donate_count);
        TextView mailText = (TextView) view.findViewById(R.id.mail_count);
                ((MainActivity) getActivity()).assembleButtons(actButton, callButton, postButton, donateButton,
                                                               mailButton, callText, postText, donateText, mailText);
        return view;
    }




}

