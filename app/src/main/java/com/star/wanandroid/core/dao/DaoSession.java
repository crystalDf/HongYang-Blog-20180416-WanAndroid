package com.star.wanandroid.core.dao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyDataDaoConfig;

    private final HistoryDataDao historyDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyDataDaoConfig = daoConfigMap.get(HistoryDataDao.class).clone();
        historyDataDaoConfig.initIdentityScope(type);

        historyDataDao = new HistoryDataDao(historyDataDaoConfig, this);

        registerDao(HistoryData.class, historyDataDao);
    }

    public void clear() {
        historyDataDaoConfig.clearIdentityScope();
    }

    public HistoryDataDao getHistoryDataDao() {
        return historyDataDao;
    }

}
