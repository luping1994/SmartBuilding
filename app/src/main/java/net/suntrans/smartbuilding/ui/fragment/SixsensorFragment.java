package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.SixSensor;
import net.suntrans.smartbuilding.data.SocketEntity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.SixsensorContract;
import net.suntrans.smartbuilding.ui.presenter.SocketContract;
import net.suntrans.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class SixsensorFragment extends BasedFragment implements SixsensorContract.View {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;
    private List<SixSensor.DataBean> datas;
    private SixsensorContract.Presenter presenter;
    private RecyclerViewAdapter<SixSensor.DataBean> adapter;
    private RequestManager manager;

    public static SixsensorFragment newInstance(String url) {
        SixsensorFragment fragment = new SixsensorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_socket;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();
        manager = Glide.with(SixsensorFragment.this);

        stateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                presenter.loadData();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter<SixSensor.DataBean>(R.layout.item_sixsensor, datas) {
            @Override
            protected void convert(BaseViewHolder helper, SixSensor.DataBean item) {
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
    protected void onFragmentFirstVisible() {
        presenter.loadData();
    }

    @Override
    public void onRetryClick() {
        presenter.loadData();
    }

    @Override
    public void setPresenter(SixsensorContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void showContent(List<SixSensor.DataBean> data) {
        if (data.size()<3){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),data.size()));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
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

    @Override
    public void onDestroyView() {
        presenter.stop();
        presenter = null;
        super.onDestroyView();
    }

}
