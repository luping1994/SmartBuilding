package net.suntrans.smartbuilding.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.App;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.activity.LoginActivity;
import net.suntrans.smartbuilding.activity.MyAreaActivity;
import net.suntrans.smartbuilding.adapter.ControlAdapter;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.model.LoginEntity;
import net.suntrans.smartbuilding.model.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ControlFragment extends RxFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MenuItemEntity.MenuBean> datas;
    private ControlAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment,container,false);
        return view;
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
        adapter = new ControlAdapter(R.layout.item_control,datas);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!datas.get(position).getLi_url().equals("#")){
                    Intent intent = new Intent(getActivity(), MyAreaActivity.class);
                    intent.putExtra("url",datas.get(position).getLi_url());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        getData();
        super.onResume();
    }

    private void getData(){
        RetrofitHelper.getApi().getMenuItem("2","1")
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
                        if (entity.getStatus()==1){
                            datas.clear();
                            datas.addAll(entity.getMenu());
                            adapter.notifyDataSetChanged();
                        }else {
                            if (entity.getMsg().equals("用户信息错误")){
                                new AlertDialog.Builder(getContext())
                                        .setCancelable(false)
                                        .setMessage("您的身份信息已过期,请重新登录")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                App.getSharedPreferences().edit().clear().commit();
                                                getActivity().finish();
                                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                            }
                                        }).create().show();
                            }
                        }

                    }
                });
    }
}
