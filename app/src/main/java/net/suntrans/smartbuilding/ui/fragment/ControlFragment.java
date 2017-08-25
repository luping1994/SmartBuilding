package net.suntrans.smartbuilding.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.activity.GlobalConActivity;
import net.suntrans.smartbuilding.ui.activity.ConSpecificActivity;
import net.suntrans.smartbuilding.ui.adapter.ControlAdapter;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.fragment.dialog.AlertDialogFragment;
import net.suntrans.smartbuilding.ui.presenter.ControlContract;
import net.suntrans.smartbuilding.utils.LogUtil;
import net.suntrans.smartbuilding.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new ControlAdapter(R.layout.item_control, datas);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.i(datas.get(position).getLi_url());
                if (datas.get(position).getLi_url().equals("Mobile_Data/MyArea")) {
                    Intent intent = new Intent(getActivity(), ConSpecificActivity.class);
                    intent.putExtra("type", Constant.TYPE_MYAREA);
                    startActivity(intent);
                } else if (datas.get(position).getLi_name().equals(getString(R.string.title_gobal_con))){
                    Intent intent = new Intent(getActivity(), GlobalConActivity.class);
                    startActivity(intent);
                }else if (datas.get(position).getLi_name().equals(getString(R.string.title_device_operation))){

                }else {
                    AlertDialogFragment dialogFragment = (AlertDialogFragment) getChildFragmentManager().findFragmentByTag(DILAOG_TAG);
                    if (dialogFragment==null){
                        dialogFragment = new AlertDialogFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg",getString(R.string.tips_developing));
                        dialogFragment.setArguments(bundle);
                    }
                    dialogFragment.show(getChildFragmentManager(),DILAOG_TAG);
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
