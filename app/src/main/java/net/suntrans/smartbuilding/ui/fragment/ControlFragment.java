package net.suntrans.smartbuilding.ui.fragment;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.App;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.activity.LoginActivity;
import net.suntrans.smartbuilding.ui.activity.MenuActivity;
import net.suntrans.smartbuilding.ui.activity.MyAreaActivity;
import net.suntrans.smartbuilding.ui.adapter.ControlAdapter;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.ControlContract;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ControlFragment extends BasedFragment implements ControlContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MenuItemEntity.MenuBean> datas;
    private ControlAdapter adapter;
    private ControlContract.Presenter presenter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getData();
//            }
//        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new ControlAdapter(R.layout.item_control, datas);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (datas.get(position).getLi_url().equals("MyArea")) {
                    Intent intent = new Intent(getActivity(), MyAreaActivity.class);
                    intent.putExtra("url", datas.get(position).getLi_url());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    intent.putExtra("menuid", datas.get(position).getLi_id());
                    intent.putExtra("name", datas.get(position).getLi_name());
                    startActivity(intent);
                }
            }
        });

        recyclerView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        presenter.loadData("1");
    }

    @Override
    public void onRetryClick() {
        super.onRetryClick();
        presenter.loadData("1");
    }


    @Override
    public void setPresenter(ControlContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showEmpty() {
        stateView.showEmpty();
    }

    @Override
    public void showLoading() {
        stateView.showLoading();
    }

    @Override
    public void showRetry() {
        stateView.showRetry();
    }

    @Override
    public void receiveWebSocketMsg(String msg) {

    }

    @Override
    public void showContent(List<MenuItemEntity.MenuBean> data) {
        datas.clear();
        datas.addAll(data);
        adapter.notifyDataSetChanged();
        stateView.showContent();
    }

    @Override
    public void onDestroyView() {
        presenter.stop();
        presenter=null;
        super.onDestroyView();
    }
}
