package com.star.wanandroid.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.base.fragment.BaseFragment;
import com.star.wanandroid.component.ACache;
import com.star.wanandroid.component.RxBus;
import com.star.wanandroid.contract.main.SettingContract;
import com.star.wanandroid.core.event.NightModeEvent;
import com.star.wanandroid.presenter.main.SettingPresenter;
import com.star.wanandroid.utils.ShareUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author quchao
 * @date 2018/4/2
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements
        SettingContract.View,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlSettingFeedback;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;

    private File cacheFile;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cacheFile = new File(Constants.PATH_CACHE);
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
        mCbSettingCache.setChecked(mPresenter.getAutoCacheState());
        mCbSettingImage.setChecked(mPresenter.getNoImageState());
        mCbSettingNight.setChecked(mPresenter.getNightModeState());
        mCbSettingCache.setOnCheckedChangeListener(this);
        mCbSettingImage.setOnCheckedChangeListener(this);
        mCbSettingNight.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.ll_setting_feedback, R.id.ll_setting_clear})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_feedback:
                ShareUtil.sendEmail(_mActivity, getString(R.string.send_email));
                break;
            case R.id.ll_setting_clear:
                ACache.deleteDir(cacheFile);
                mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_night:
                mPresenter.setNightModeState(b);
                NightModeEvent nightModeEvent = new NightModeEvent();
                nightModeEvent.setNightMode(b);
                RxBus.getDefault().post(nightModeEvent);
                break;
            case R.id.cb_setting_image:
                mPresenter.setNoImageState(b);
                break;
            case R.id.cb_setting_cache:
                mPresenter.setAutoCacheState(b);
                break;
            default:
                break;
        }
    }

    public static SettingFragment getInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

}
