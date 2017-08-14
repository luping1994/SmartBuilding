package net.suntrans.smartbuilding.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.suntrans.smartbuilding.ui.fragment.AnalysisFragment;
import net.suntrans.smartbuilding.ui.fragment.ControlFragment;
import net.suntrans.smartbuilding.ui.fragment.SmartFragment;
import net.suntrans.smartbuilding.ui.fragment.UserCenterFragment;
import net.suntrans.smartbuilding.ui.presenter.impl.ControlPresenter;

/**
 * Created by Looney on 2017/7/20.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private final ControlPresenter presenter1;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        ControlFragment fragment1 = new ControlFragment();
        presenter1 = new ControlPresenter(fragment1);

        AnalysisFragment fragment2 = new AnalysisFragment();


        SmartFragment fragment3 = new SmartFragment();
        UserCenterFragment fragment4 = new UserCenterFragment();
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

    public void detach(){
        presenter1.onUnsubscribe();
    }
}
