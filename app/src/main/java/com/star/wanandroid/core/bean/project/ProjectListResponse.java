package com.star.wanandroid.core.bean.project;

import com.star.wanandroid.core.bean.BaseResponse;

public class ProjectListResponse extends BaseResponse {

    private ProjectListData data;

    public ProjectListData getData() {
        return data;
    }

    public void setData(ProjectListData data) {
        this.data = data;
    }
}
