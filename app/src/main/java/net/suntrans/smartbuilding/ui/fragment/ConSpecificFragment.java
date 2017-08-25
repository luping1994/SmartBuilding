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
import net.suntrans.smartbuilding.ui.presenter.AirContract;
import net.suntrans.smartbuilding.ui.presenter.impl.AirPresenter;
import net.suntrans.smartbuilding.ui.presenter.impl.LightPresenter;
import net.suntrans.smartbuilding.ui.presenter.impl.ScenePresenter;
import net.suntrans.smartbuilding.ui.presenter.impl.SixsensorPresenter;
import net.suntrans.smartbuilding.ui.presenter.impl.SocketPresenter;
import net.suntrans.smartbuilding.ui.presenter.impl.XenonPresenter;

/**
 * Created by Administrator on 2017/8/9.
 */

public class ConSpecificFragment extends RxFragment {

    private final String TAG = getClass().getSimpleName();
    private String type;
    private String areaid;


    public static ConSpecificFragment newInstance(String type,String areaid) {
        ConSpecificFragment fragment = new ConSpecificFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("areaid", areaid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
        areaid = getArguments().getString("areaid");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SceneFragment sceneFragment = SceneFragment.newInstance(type);
        LightFragment lightFragment = LightFragment.newInstance(type);
        AirConditioningFragment airConditioningFragment = AirConditioningFragment.newInstance(type);
        XenonFragment xenonFragment = new XenonFragment();
        SocketFragment socketFragment = SocketFragment.newInstance(type);
        SixsensorFragment fragment = new SixsensorFragment();

        ScenePresenter presenter1 = new ScenePresenter(sceneFragment, type,areaid);
        LightPresenter presenter2 = new LightPresenter(lightFragment,type,areaid);
        AirContract.Presenter presenter3 = new AirPresenter(airConditioningFragment, type,areaid);
        XenonPresenter presenter4 = new XenonPresenter(xenonFragment, type,areaid);
        SocketPresenter presenter5 = new SocketPresenter(socketFragment, type,areaid);
        SixsensorPresenter presenter6 = new SixsensorPresenter(fragment, type,areaid);


        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addFragment(sceneFragment, getString(R.string.title_model));
        adapter.addFragment(lightFragment, "照明");
        adapter.addFragment(airConditioningFragment, "空调");
        adapter.addFragment(xenonFragment, "氙气灯");
        adapter.addFragment(socketFragment, "插座");
        adapter.addFragment(fragment, "第六感");
//        adapter.addFragment(SceneFragment.newInstance(url),"其他");

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(6);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }


}
