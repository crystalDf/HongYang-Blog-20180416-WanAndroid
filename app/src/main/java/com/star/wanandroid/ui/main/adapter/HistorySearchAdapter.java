package com.star.wanandroid.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.star.wanandroid.R;
import com.star.wanandroid.core.dao.HistoryData;
import com.star.wanandroid.ui.main.viewholder.SearchHistoryViewHolder;
import com.star.wanandroid.utils.CommonUtils;

import java.util.List;

/**
 * @author quchao
 * @date 2018/3/23
 */

public class HistorySearchAdapter extends BaseQuickAdapter<HistoryData, SearchHistoryViewHolder> {

    public HistorySearchAdapter(int layoutResId, @Nullable List<HistoryData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(SearchHistoryViewHolder helper, HistoryData historyData) {
        helper.setText(R.id.item_search_history_tv, historyData.getData());
        helper.setTextColor(R.id.item_search_history_tv, CommonUtils.randomColor());

        helper.addOnClickListener(R.id.item_search_history_tv);
    }
}
