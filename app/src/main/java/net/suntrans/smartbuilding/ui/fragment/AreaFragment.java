package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.adapter.FragmentAdapter;
import net.suntrans.smartbuilding.ui.presenter.SceneContract;
import net.suntrans.smartbuilding.ui.presenter.impl.ScenePresenter;

/**
 * Created by Administrator on 2017/8/9.
 */

public class AreaFragment extends RxFragment {

    private final String TAG = getClass().getSimpleName();
    private String url;
    private ScenePresenter presenter;

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
        SceneFragment sceneFragment = SceneFragment.newInstance(url);
        presenter = new ScenePresenter(sceneFragment);

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addFragment(sceneFragment,"模式");
        adapter.addFragment(LightFragment.newInstance(url),"照明");
        adapter.addFragment(AirConditioningFragment.newInstance(url),"空调");
        adapter.addFragment(new XenonFragment(),"氙气灯");
        adapter.addFragment(SceneFragment.newInstance(url),"第六感");
        adapter.addFragment(SceneFragment.newInstance(url),"其他");

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(6);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
