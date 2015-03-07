package layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class SquareRelativeLayout extends RelativeLayout
{
    public SquareRelativeLayout(Context context) {
        super(context);
    }
    public SquareRelativeLayout(Context context,AttributeSet attrs) {
        super(context,attrs);
    }
    public SquareRelativeLayout(Context context,AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
    }
    @Override
    public void onMeasure(int widthMeasure,int heightMeasure) {
        super.onMeasure(widthMeasure,widthMeasure);
    }

}
