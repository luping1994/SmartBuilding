package net.suntrans.smartbuilding.fragment;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.adapter.AreaFragmentAdapter;
import net.suntrans.smartbuilding.widgets.IViewPager;

/**
 * Created by Administrator on 2017/8/9.
 */

public class AreaFragment extends RxFragment {

    private final String TAG = getClass().getSimpleName();
    private String url;

    public static AreaFragment newInstance(String url) {
        AreaFragment fragment = new AreaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        AreaFragmentAdapter adapter = new AreaFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(ModeFragment.newInstance(url),"模式");
        adapter.addFragment(ModeFragment.newInstance(url),"照明");
        adapter.addFragment(ModeFragment.newInstance(url),"空调");
        adapter.addFragment(ModeFragment.newInstance(url),"氙气灯");
        url = getArguments().getString("url");

        ViewPager viewPager = (IViewPager) view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);
    }
}
