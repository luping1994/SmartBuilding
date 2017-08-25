package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.LightContract;
import net.suntrans.smartbuilding.utils.UiUtils;
import net.suntrans.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class LightFragment extends BasedFragment implements LightContract.View {
    private final String TAG = getClass().getSimpleName();
    private String url;
    private RecyclerView recyclerView;
    private List<LightEntity.DataBean> datas;
    private LightContract.Presenter presenter;
    private RecyclerViewAdapter<LightEntity.DataBean> adapter;
    private RequestManager manager;

    public static LightFragment newInstance(String url) {
        LightFragment fragment = new LightFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_light;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();
        manager = Glide.with(LightFragment.this);

        stateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                presenter.loadData();
            }
        });
        url = getArguments().getString("url");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter<LightEntity.DataBean>(R.layout.item_light, datas) {
            @Override
            protected void convert(BaseViewHolder helper, LightEntity.DataBean item) {
                helper.setText(R.id.name, item.getName());
                ImageView imageView = helper.getView(R.id.image);
                imageView.setImageResource(item.getStatus().equals("1") ? R.drawable.ic_light_on : R.drawable.ic_light_off);
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
    public void setPresenter(LightContract.Presenter presenter) {
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
    public void showContent(List<LightEntity.DataBean> data) {
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
