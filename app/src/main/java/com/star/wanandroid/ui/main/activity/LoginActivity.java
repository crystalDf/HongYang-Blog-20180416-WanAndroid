package com.star.wanandroid.ui.main.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding2.view.RxView;
import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.activity.BaseActivity;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.main.LoginContract;
import com.star.wanandroid.core.bean.main.login.LoginData;
import com.star.wanandroid.core.event.LoginEvent;
import com.star.wanandroid.presenter.main.LoginPresenter;
import com.star.wanandroid.utils.CommonUtils;
import com.star.wanandroid.utils.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.login_group)
    RelativeLayout mLoginGroup;
    @BindView(R.id.login_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.login_account_edit)
    EditText mAccountEdit;
    @BindView(R.id.login_password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_register_btn)
    Button mRegisterBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mPresenter.addRxBindingSubscribe(RxView.clicks(mLoginBtn)
                .throttleFirst(Constants.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> mPresenter != null)
                .subscribe(o -> {
                    String account = mAccountEdit.getText().toString().trim();
                    String password = mPasswordEdit.getText().toString().trim();
                    if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                        CommonUtils.showSnackMessage(this, getString(R.string.account_password_null_tint));
                        return;
                    }
                    mPresenter.getLoginData(account, password);
                }));
    }

    @Override
    public void showLoginData(LoginData loginData) {
        mPresenter.setLoginAccount(loginData.getUsername());
        mPresenter.setLoginPassword(loginData.getPassword());
        mPresenter.setLoginStatus(true);
        RxBus.getDefault().post(new LoginEvent(true));
        CommonUtils.showSnackMessage(this, getString(R.string.login_success));
        onBackPressedSupport();
    }

    @Override
    public void showRegisterData(LoginData loginData) {
        mPresenter.getLoginData(loginData.getUsername(), loginData.getPassword());
    }

    @OnClick({R.id.login_register_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_register_btn:
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(mRegisterBtn,
                        mRegisterBtn.getWidth() / 2,
                        mRegisterBtn.getHeight() / 2, 0 , 0);
                startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                break;
            default:
                break;
        }
    }
}
