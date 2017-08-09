package net.suntrans.smartbuilding.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.adapter.ControlAdapter;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.model.MenuItemEntity;
import net.suntrans.smartbuilding.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/8.
 */

public class AnalysisFragment extends RxFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MenuItemEntity.MenuBean> datas;
    private ControlAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        View view1 = view.findViewById(R.id.fixView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view1.setVisibility(View.VISIBLE);
        } else {
            view1.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onResume() {
        getData();
        super.onResume();
    }

    private void getData() {
        RetrofitHelper.getApi().getMenuItem("2", "1")
                .compose(this.<MenuItemEntity>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MenuItemEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(MenuItemEntity entity) {
//                        swipeRefreshLayout.setRefreshing(false);

                        datas.clear();
                        datas.addAll(entity.getMenu());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
