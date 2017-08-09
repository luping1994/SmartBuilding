package net.suntrans.smartbuilding.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.suntrans.smartbuilding.fragment.AnalysisFragment;
import net.suntrans.smartbuilding.fragment.ControlFragment;
import net.suntrans.smartbuilding.fragment.SmartFragment;

/**
 * Created by Looney on 2017/7/20.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        ControlFragment fragment1 = new ControlFragment();
        AnalysisFragment fragment2 = new AnalysisFragment();
        SmartFragment fragment3 = new SmartFragment();
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
