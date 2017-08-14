package net.suntrans.smartbuilding.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.ui.activity.MenuActivity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.ui.presenter.MenuContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MenuFragment extends BasedFragment implements MenuContract.View{

    protected final String TAG = getClass().getSimpleName();
    protected String menuid;
    protected RecyclerView recyclerView;
    protected List<MenuItemEntity.MenuBean> datas;
    private RecyclerViewAdapter<MenuItemEntity.MenuBean> adapter;
    private MenuContract.Presenter presenter;


    public static MenuFragment newInstance(String menuid) {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("menuid", menuid);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.recyclerview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        menuid = getArguments().getString("menuid");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<MenuItemEntity.MenuBean>(R.layout.item_menu, datas) {
            @Override
            protected void convert(BaseViewHolder helper, MenuItemEntity.MenuBean item) {
                helper.setText(R.id.title, item.getLi_name());
                Picasso.with(getContext())
                        .load(item.getLi_img())
                        .placeholder(R.drawable.analysys)
                        .resize(getResources().getDimensionPixelSize(R.dimen.menuItemPicSize),getResources().getDimensionPixelSize(R.dimen.menuItemPicSize))
                        .into((ImageView) helper.getView(R.id.image));
            }
        };
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
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));

        super.onViewCreated(view,savedInstanceState);

    }


    @Override
    protected void onFragmentFirstVisible() {
        presenter.loadData(menuid);

    }

    @Override
    public void onRetryClick() {
       presenter.loadData(menuid);
    }

    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
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
    public void showContent(List<MenuItemEntity.MenuBean> data) {
        datas.clear();
        datas.addAll(data);
        adapter.notifyDataSetChanged();
        stateView.showContent();
    }
}
