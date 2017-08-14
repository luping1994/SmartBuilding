package net.suntrans.smartbuilding.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.components.support.RxFragment;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.activity.MenuActivity;
import net.suntrans.smartbuilding.ui.adapter.ControlAdapter;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.base.LazyLoadFragment;
import net.suntrans.smartbuilding.utils.UiUtils;
import net.suntrans.stateview.StateView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by Administrator on 2017/8/8.
 */

public class AnalysisFragment extends BasedFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MenuItemEntity.MenuBean> datas;
    private RecyclerViewAdapter<MenuItemEntity.MenuBean> adapter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_analysis;
    }

    @Override
    protected void initView(View rootView) {
        RelativeLayout toolBar = (RelativeLayout) rootView.findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionbarAndStatusSize);
            toolBar.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionBarSize);
            toolBar.setLayoutParams(lp);
        }
        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setText(R.string.nav_title_analysis);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        datas = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<MenuItemEntity.MenuBean>(R.layout.item_analysis, datas) {
            @Override
            protected void convert(BaseViewHolder helper, MenuItemEntity.MenuBean item) {
                helper.setText(R.id.title,item.getLi_name());
                Picasso.with(getContext())
                        .load(item.getLi_img())
                        .placeholder(R.drawable.analysys)
                        .resize(getResources().getDimensionPixelSize(R.dimen.menuItemPicSize),getResources().getDimensionPixelSize(R.dimen.menuItemPicSize))
                        .into((ImageView) helper.getView(R.id.image));
            }
        };
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (datas.get(position).getLi_url().equals("#")) {
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    intent.putExtra("menuid", datas.get(position).getLi_id());
                    intent.putExtra("name", datas.get(position).getLi_name());
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible)
            getData();

    }



    @Override
    public void onRetryClick() {
        getData();
    }



    private void getData() {
        stateView.showLoading();
        RetrofitHelper.getApi().getMenuItem("2")
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
                        stateView.showRetry();
                    }

                    @Override
                    public void onNext(MenuItemEntity entity) {
                        if (entity.getStatus()==1){
                            if (entity.getMenu()==null||entity.getMenu().size()==0){
                                stateView.showEmpty();
                                UiUtils.showToast(getString(R.string.tips_isempty));
                                return;
                            }
                            datas.clear();
                            datas.addAll(entity.getMenu());
                            adapter.notifyDataSetChanged();
                            stateView.showContent();

                        }else {
                            stateView.showRetry();
                            UiUtils.showToast(entity.getMsg());
                        }

                    }
                });
    }
}
