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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class AreaBasedFragment<T> extends RxFragment {

    protected final String TAG = getClass().getSimpleName();
    protected String url;
    protected RecyclerView recyclerView;
    protected List<T> datas;
    private RecyclerViewAdapter<T> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
//        url = getArguments().getString("url");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter<T>(getRecyclerViewItemId(), datas) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                AreaBasedFragment.this.convert(helper, item);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AreaBasedFragment.this.onItemClick(adapter,view,position);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        super.onViewCreated(view,savedInstanceState);
    }

    protected int getLayoutId() {
        return -1;
    }

    protected int getRecyclerViewItemId() {
        return -1;
    }

    protected void convert(BaseViewHolder helper, T item) {
    }

    protected List<T> getData(){
        return null;
    }

    protected void onItemClick(BaseQuickAdapter adapter, View view, int position){

    }

    @Override
    public void onResume() {
        super.onResume();
        datas.clear();
        datas.addAll(getData());
    }
}
