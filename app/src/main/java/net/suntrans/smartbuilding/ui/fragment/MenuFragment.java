package net.suntrans.smartbuilding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import net.suntrans.smartbuilding.data.MenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MenuFragment extends RxFragment {

    protected final String TAG = getClass().getSimpleName();
    protected String menuid;
    protected RecyclerView recyclerView;
    protected List<MenuEntity> datas;
    private RecyclerViewAdapter<MenuEntity> adapter;


    public static MenuFragment newInstance(String menuid) {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("menuid", menuid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        menuid = getArguments().getString("menuid");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<MenuEntity>(R.layout.item_menu, datas) {
            @Override
            protected void convert(BaseViewHolder helper, MenuEntity item) {
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
    }
}
