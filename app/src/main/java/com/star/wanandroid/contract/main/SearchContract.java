package com.star.wanandroid.contract.main;

import com.star.wanandroid.base.presenter.AbstractPresenter;
import com.star.wanandroid.base.view.BaseView;

public interface SearchContract {

    interface View extends BaseView {

        /**
         * Show history data
         *
         * @param historyDataList List<HistoryData>
         */
        void showHistoryData(List<HistoryData> historyDataList);

        /**
         * Show top search data
         *
         * @param topSearchDataResponse BaseResponse<List<TopSearchData>>
         */
        void showTopSearchData(BaseResponse<List<TopSearchData>> topSearchDataResponse);

        /**
         * Show top search data fail
         */
        void showTopSearchDataFail();

        /**
         * Judge to the search list activity
         */
        void judgeToTheSearchListActivity();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Load all history data
         *
         * @return all history data
         */
        List<HistoryData> loadAllHistoryData();

        /**
         * Add history data
         *
         * @param data history data
         */
        void addHistoryData(String data);

        /**
         * 热搜
         */
        void getTopSearchData();

        /**
         * Clear history data
         */
        void clearHistoryData();
    }
}
