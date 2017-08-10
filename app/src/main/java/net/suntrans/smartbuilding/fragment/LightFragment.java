package net.suntrans.smartbuilding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle.components.support.RxFragment;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.model.LightEntity;
import net.suntrans.smartbuilding.model.ModeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class LightFragment extends RxFragment {
    private final String TAG = getClass().getSimpleName();
    private String url;
    private RecyclerView recyclerView;
    private List<LightEntity> datas;
    public static LightFragment newInstance(String url) {
        LightFragment fragment = new LightFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_light, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();
        for (int i=0;i<7;i++){
            LightEntity mode = new LightEntity();
            datas.add(mode);
        }
        url = getArguments().getString("url");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter<LightEntity> adapter = new RecyclerViewAdapter<LightEntity>(R.layout.item_light,datas){
            @Override
            protected void convert(BaseViewHolder helper, LightEntity item) {
                super.convert(helper, item);
            }
        };
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapter);
    }
}
