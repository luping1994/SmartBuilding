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

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.fragment.ConSpecificFragment;

/**
 * Created by Looney on 2017/8/9.
 * 控制页面 具体操作设备的activity，example:场景、氙气灯等内容的具体操作
 */
public class ConSpecificActivity extends RxAppCompatActivity {
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
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionbarAndStatusSize);
            toolBar.setLayoutParams(lp);
        }else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) toolBar.getLayoutParams();
            lp.height = getResources().getDimensionPixelSize(R.dimen.actionBarSize);
            toolBar.setLayoutParams(lp);
        }
        String type = getIntent().getStringExtra("type");
        TextView tv = (TextView) findViewById(R.id.title);
        String areaid = "";
        if (type.equals(Constant.TYPE_MYAREA)){
            tv.setText(getResources().getString(R.string.title_my_area));
        }else {
            tv.setText(getIntent().getStringExtra("titleName"));
            areaid = getIntent().getStringExtra("areaid");
        }
        ConSpecificFragment fragment =  ConSpecificFragment.newInstance(type,areaid);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment,AREALIST)
                .commit();
    }
}
