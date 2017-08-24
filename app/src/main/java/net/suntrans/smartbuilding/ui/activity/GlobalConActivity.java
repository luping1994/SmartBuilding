package net.suntrans.smartbuilding.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.ui.fragment.GlobalConFragment;
import net.suntrans.smartbuilding.utils.LogUtil;

import java.util.Stack;

/**
 * Created by Administrator on 2017/8/9.
 */
public class GlobalConActivity extends RxAppCompatActivity implements GlobalConFragment.OnFragmentInteractionListener {
    private final String AREALIST = "AREALIST_ONE";
    private TextView tv;
    private Stack<String> titles = new Stack<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_con);
        RelativeLayout toolBar = (RelativeLayout) findViewById(R.id.toolbar);
        findViewById(R.id.back)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
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
        titles.push(getResources().getString(R.string.title_global));
        tv = (TextView) findViewById(R.id.title);
        tv.setText(getResources().getString(R.string.title_global));
        GlobalConFragment fragment = GlobalConFragment.newInstance("1");
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment, AREALIST)
                .commit();
    }

    @Override
    public void setToolBarTitle(String title) {
        tv.setText(title);
        titles.push(title);
    }

    @Override
    public void onItemSelected(String id) {
        GlobalConFragment fragment = GlobalConFragment.newInstance(id);
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.replace(R.id.content, fragment, id);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            titles.pop();
            String title = titles.peek();
            tv.setText(title);
        } else {
            // Lastly, it will rely on the system behavior for back
            super.onBackPressed();
        }
    }
}
