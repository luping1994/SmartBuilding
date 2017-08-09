package net.suntrans.smartbuilding.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.model.MenuItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class ControlAdapter extends BaseQuickAdapter<MenuItemEntity.MenuBean, BaseViewHolder> {


    public ControlAdapter(@LayoutRes int layoutResId, @Nullable List<MenuItemEntity.MenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuItemEntity.MenuBean item) {
        helper.setText(R.id.name,item.getLi_name());
        if (helper.getAdapterPosition()==0){
            helper.getView(R.id.decoration).setVisibility(View.INVISIBLE);
        }else {
            helper.getView(R.id.decoration).setVisibility(View.VISIBLE);

        }
    }
}
