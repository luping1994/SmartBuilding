package net.suntrans.smartbuilding.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.data.XenonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class XenonFragment extends AreaBasedFragment<XenonEntity> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xenon;
    }

    @Override
    protected int getRecyclerViewItemId() {
        return R.layout.item_xenon;
    }

    @Override
    protected void convert(BaseViewHolder helper, XenonEntity item) {
        super.convert(helper, item);
    }

    @Override
    protected List<XenonEntity> getData() {
        List<XenonEntity> da =new ArrayList<>();
        for (int i=0;i<7;i++){
            XenonEntity entity = new XenonEntity();
            da.add(entity);
        }
        return da;
    }

    @Override
    protected void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
