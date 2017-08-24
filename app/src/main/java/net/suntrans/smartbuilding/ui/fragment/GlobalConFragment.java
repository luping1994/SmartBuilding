package net.suntrans.smartbuilding.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.data.AreaEntity;
import net.suntrans.smartbuilding.ui.activity.ConSpecificActivity;
import net.suntrans.smartbuilding.ui.adapter.DividerItemDecoration;
import net.suntrans.smartbuilding.ui.adapter.RecyclerViewAdapter;
import net.suntrans.smartbuilding.ui.base.BasedFragment;
import net.suntrans.smartbuilding.utils.LogUtil;
import net.suntrans.smartbuilding.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalConFragment extends BasedFragment {
    private final String TAG = getClass().getSimpleName();
    private String url;
    private RecyclerView recyclerView;
    private List<AreaEntity.DataBean> datas = new ArrayList<>();
    private RecyclerViewAdapter<AreaEntity.DataBean> adapter;
    private RequestManager manager;


    private String id;

    public static GlobalConFragment newInstance(String id) {
        GlobalConFragment fragment = new GlobalConFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_global_con;
    }

    @Override
    public void onRetryClick() {
        getData();
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        manager = Glide.with(GlobalConFragment.this);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter<AreaEntity.DataBean>(R.layout.item_area, datas) {
            @Override
            protected void convert(BaseViewHolder helper, AreaEntity.DataBean item) {
                helper.setText(R.id.name, item.ar_name);
                ImageView imageView = helper.getView(R.id.image);
                manager.load(item.ar_img)
                        .override(UiUtils.dip2px(72), UiUtils.dip2px(72))
                        .placeholder(R.drawable.icon_guanliqv)
                        .into(imageView);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (datas.get(position).ar_url.equals("#")) {
                    mListener.onItemSelected(datas.get(position).ar_id);
                    mListener.setToolBarTitle(datas.get(position).ar_name);
                } else {
                    Intent intent = new Intent(getActivity(), ConSpecificActivity.class);
                    intent.putExtra("type", Constant.TYPE_GLOBAL);
                    intent.putExtra("titleName", datas.get(position).ar_name);
                    intent.putExtra("areaid", datas.get(position).ar_id);
                    LogUtil.i(TAG,datas.get(position).ar_id);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void onFragmentFirstVisible() {
        getData();
    }


    private void getData() {
        recyclerView.setVisibility(View.INVISIBLE);
        stateView.showLoading();
        RetrofitHelper.getApi().getGlobalList(id)
                .compose(this.<AreaEntity>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AreaEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        stateView.showRetry();
                        recyclerView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(AreaEntity areaEntity) {
                        stateView.showContent();
                        recyclerView.setVisibility(View.VISIBLE);
                        datas.clear();

                        datas.addAll(areaEntity.data);
                        adapter.notifyDataSetChanged();
                    }
                });

    }


    private OnFragmentInteractionListener mListener;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void setToolBarTitle(String title);

        void onItemSelected(String id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
