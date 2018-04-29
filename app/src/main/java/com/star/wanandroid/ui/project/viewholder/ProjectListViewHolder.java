package com.star.wanandroid.ui.project.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.star.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class ProjectListViewHolder extends BaseViewHolder{

    @BindView(R.id.item_project_list_iv)
    ImageView mProjectIv;
    @BindView(R.id.item_project_list_title_tv)
    TextView mTitleTv;
    @BindView(R.id.item_project_list_content_tv)
    TextView mContentTv;
    @BindView(R.id.item_project_list_time_tv)
    TextView mTimeTv;
    @BindView(R.id.item_project_list_author_tv)
    TextView mAuthorTv;
    @BindView(R.id.item_project_list_install_tv)
    TextView mInstallTv;

    public ProjectListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
