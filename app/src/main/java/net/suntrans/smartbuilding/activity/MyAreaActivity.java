package net.suntrans.smartbuilding.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.fragment.AreaFragment;
import net.suntrans.smartbuilding.utils.UiUtils;

/**
 * Created by Administrator on 2017/8/9.
 */
public class MyAreaActivity extends RxAppCompatActivity {
    private final String AREALIST ="AREALIST";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myarea);
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
            lp.height = UiUtils.dip2px(74);
            toolBar.setLayoutParams(lp);
        }else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = UiUtils.dip2px(50);
            toolBar.setLayoutParams(lp);
        }
        AreaFragment fragment =  AreaFragment.newInstance(getIntent().getStringExtra("url"));
        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment,AREALIST)
                .commit();
    }
}
