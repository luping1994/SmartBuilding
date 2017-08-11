package net.suntrans.smartbuilding.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle.components.support.RxFragment;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.adapter.ControlAdapter;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.data.MenuItemEntity;
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
        RelativeLayout toolBar = (RelativeLayout) view.findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionbarAndStatusSize);
            toolBar.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionBarSize);
            toolBar.setLayoutParams(lp);
        }
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("分析");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        MenuItemEntity.MenuBean menuBean = new MenuItemEntity.MenuBean();
        menuBean.setLi_name("能耗分析");
        datas.add(menuBean);
        MenuItemEntity.MenuBean menuBean1 = new MenuItemEntity.MenuBean();
        menuBean.setLi_name("环境分析");
        datas.add(menuBean1);
        MenuItemEntity.MenuBean menuBean2 = new MenuItemEntity.MenuBean();
        menuBean.setLi_name("状态评估");
        datas.add(menuBean2);
        MenuItemEntity.MenuBean menuBean3 = new MenuItemEntity.MenuBean();
        menuBean.setLi_name("配电图");
        datas.add(menuBean3);

        //StaggeredGridLayoutManager

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter<MenuItemEntity.MenuBean> adapter = new RecyclerViewAdapter<MenuItemEntity.MenuBean>(R.layout.item_analysis, datas) {
            @Override
            protected void convert(BaseViewHolder helper, MenuItemEntity.MenuBean item) {
                super.convert(helper, item);
            }
        };
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public void onResume() {
//        getData();
        super.onResume();
    }

    private void getData() {
        RetrofitHelper.getApi().getMenuItem("1")
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
