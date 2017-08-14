package net.suntrans.smartbuilding.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.fragment.AreaFragment;
import net.suntrans.smartbuilding.ui.fragment.MenuFragment;
import net.suntrans.smartbuilding.ui.presenter.impl.MenuPresenter;
import net.suntrans.smartbuilding.utils.ActivityUtils;

/**
 * Created by Administrator on 2017/8/9.
 */
public class MenuActivity extends RxAppCompatActivity {

    private MenuPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RelativeLayout toolBar = (RelativeLayout) findViewById(R.id.toolbar);
        findViewById(R.id.back)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionbarAndStatusSize);
            toolBar.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionBarSize);
            toolBar.setLayoutParams(lp);
        }
        TextView tv = (TextView) findViewById(R.id.title);
        tv.setText(getIntent().getStringExtra("name"));


        MenuFragment fragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragment == null) {
            fragment = MenuFragment.newInstance(getIntent().getStringExtra("menuid"));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
        }
        presenter = new MenuPresenter(fragment);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onUnsubscribe();
    }
}
