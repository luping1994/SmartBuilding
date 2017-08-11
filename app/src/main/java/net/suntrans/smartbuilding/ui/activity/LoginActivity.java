package net.suntrans.smartbuilding.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import net.suntrans.smartbuilding.App;
import net.suntrans.smartbuilding.MainActivity;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.api.RetrofitHelper;
import net.suntrans.smartbuilding.data.LoginEntity;
import net.suntrans.smartbuilding.utils.UiUtils;
import net.suntrans.smartbuilding.ui.widgets.EditView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/9.
 */

public class LoginActivity extends RxAppCompatActivity {

    private EditView account;
    private EditView password;
    private Button button;
    private LinearLayout ll1;
    private LinearLayout ll2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = (EditView) findViewById(R.id.account);
        password = (EditView) findViewById(R.id.password);
        button = (Button) findViewById(R.id.login);
        ll1 = (LinearLayout) findViewById(R.id.accountLL);
        ll2 = (LinearLayout) findViewById(R.id.passwordLL);

        check();

    }

    private void check() {
        String token = App.getSharedPreferences().getString("token", "-1");
        if (!token.equals("-1")){
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {
        String passwords =password.getText().toString();
        String accounts = account.getText().toString();
        if (TextUtils.isEmpty(accounts)){
            account.setError("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(passwords)) {
            password.setError("密码不能为空");
            return;
        }
        login(accounts,passwords);

    }

    private void login(String  account,String password) {
        RetrofitHelper.getApi()
                .login(account,password)
                .compose(this.<LoginEntity>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        UiUtils.showToast("服务器错误");
                    }

                    @Override
                    public void onNext(LoginEntity entity) {
                        if (entity.status==1){
                            App.getSharedPreferences().edit().putString("token",entity.data.token)
                                    .putString("group",entity.data.group)
                                    .putString("id",entity.data.id)
                                    .commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            UiUtils.showToast(entity.msg);
                        }
                    }
                });
    }
}
