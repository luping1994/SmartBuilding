package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.XenonEntity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.XenonContract;
import net.suntrans.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class XenonFragment extends BasedFragment implements XenonContract.View {

    private XenonContract.Presenter presenter;
    private List<XenonEntity.DataBean> datas;
    private RequestManager manager;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_xenon;
    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        if (presenter != null)
            presenter.loadData();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();
        manager = Glide.with(this);

        stateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                presenter.loadData();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter<XenonEntity.DataBean>(R.layout.item_xenon, datas) {
            @Override
            protected void convert(BaseViewHolder helper, XenonEntity.DataBean item) {
                helper.setText(R.id.name, item.name);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void setPresenter(XenonContract.Presenter presenter) {
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
    public void showContent(List<XenonEntity.DataBean> data) {
        if (data.size()<3){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),data.size()));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        }
        if (recyclerView.getAdapter()==null){
            recyclerView.setAdapter(adapter);
        }
        stateView.showContent();
        this.datas.clear();
        this.datas.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        presenter.stop();
        presenter = null;
        super.onDestroyView();
    }
}
