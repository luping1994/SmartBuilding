package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.SceneContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class SceneFragment extends BasedFragment implements SceneContract.View{
    private final String TAG = getClass().getSimpleName();
    private String url;
    private RecyclerView recyclerView;
    private List<SceneEntity.Scene> datas;
    private SceneContract.Presenter presenter;
    private RecyclerViewAdapter<SceneEntity.Scene> adapter;

    public static SceneFragment newInstance(String url) {
        SceneFragment fragment = new SceneFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_mode;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        url = getArguments().getString("url");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<SceneEntity.Scene>(R.layout.item_mode,datas){
            @Override
            protected void convert(BaseViewHolder helper, SceneEntity.Scene item) {
                helper.setText(R.id.name, item.getName());
                Picasso.with(getContext())
                        .load(item.getImg_url())
                        .placeholder(R.drawable.icon_xiaban)
                        .resize(getResources().getDimensionPixelSize(R.dimen.menuItemPicSize),getResources().getDimensionPixelSize(R.dimen.menuItemPicSize))
                        .into((ImageView) helper.getView(R.id.image));
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        presenter.loadData();
    }

    @Override
    public void setPresenter(SceneContract.Presenter presenter) {
        this.presenter =presenter;
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
    public void showContent(List<SceneEntity.Scene> data) {
        this.datas.clear();
        this.datas.addAll(data);
        adapter.notifyDataSetChanged();
        stateView.showContent();
    }
}
