package net.suntrans.smartbuilding.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import net.suntrans.smartbuilding.fragment.ControlFragment;
/**
 * Created by Looney on 2017/7/20.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        ControlFragment fragment1 = new ControlFragment();
        ControlFragment fragment2 = new ControlFragment();
        ControlFragment fragment3 = new ControlFragment();
        ControlFragment fragment4 = new ControlFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3, fragment4};
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
