package adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

        import java.util.ArrayList;
        import java.util.List;

public class MainActivityPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MainActivityPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}