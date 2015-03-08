package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.dstny.activities.R;

public class ListAdapter extends BaseAdapter {

    private ArrayList<String> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;

    public ListAdapter(Context context, ArrayList<String> list, Activity activity) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        RelativeLayout itemBody;
        TextView label;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String text = list.get(position);
        holder.itemBody = (RelativeLayout) convertView.findViewById(R.id.list_item_container);
        holder.label = (TextView) convertView.findViewById(R.id.item_label);

        int swatch = position % 4;
        switch (swatch) {
            case 0:
                holder.itemBody.setBackgroundResource(R.color.orange);
                break;
            case 1:
                holder.itemBody.setBackgroundResource(R.color.lightblue);
                break;
            case 2:
                holder.itemBody.setBackgroundResource(R.color.darkblue);
                break;
            case 3:
                holder.itemBody.setBackgroundResource(R.color.pink);
                break;
        }
        holder.label.setText(text);

        return convertView;
    }
}