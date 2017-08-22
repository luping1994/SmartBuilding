package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.data.AirConditionEntity;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.AirContract;
import net.suntrans.smartbuilding.ui.presenter.impl.AirPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class AirConditioningFragment extends BasedFragment implements AirContract.View{
    private final String TAG = getClass().getSimpleName();
    private String url;
    private RecyclerView recyclerView;
    private List<AirConditionEntity.DataBean> datas;
    private AirContract.Presenter presenter;
    private RecyclerViewAdapter<AirConditionEntity.DataBean> adapter;

    public static AirConditioningFragment newInstance(String url) {
        AirConditioningFragment fragment = new AirConditioningFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_ari;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();

        url = getArguments().getString("url");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<AirConditionEntity.DataBean>(R.layout.item_air,datas){
            @Override
            protected void convert(BaseViewHolder helper, AirConditionEntity.DataBean item) {
               helper.setText(R.id.name,item.name);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        super.onViewCreated(view,savedInstanceState);
    }


    @Override
    protected void onFragmentFirstVisible() {
        presenter.loadData();
    }

    @Override
    public void onRetryClick() {
        presenter.loadData();
    }

    @Override
    public void setPresenter(AirContract.Presenter presenter) {
        this.presenter  = presenter;
    }

    @Override
    public void showEmpty() {
        stateView.showEmpty();
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        stateView.showLoading();
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRetry() {
        stateView.showRetry();
        recyclerView.setVisibility(View.INVISIBLE);

    }


    @Override
    public void receiveWebSocketMsg(String msg) {

    }

    @Override
    public void showContent(List<AirConditionEntity.DataBean> data) {
        if (data.size()<2){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),data.size()));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }
        if (recyclerView.getAdapter()==null){
            recyclerView.setAdapter(adapter);
        }
        datas.clear();
        datas.addAll(data);
        adapter.notifyDataSetChanged();
        stateView.showContent();
        recyclerView.setVisibility(View.VISIBLE);
    }
}
