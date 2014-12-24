package com.tcapps.hearthtools.module;

import dagger.Module;
import dagger.Provides;
import android.content.Context;
import com.tcapps.hearthtools.database.HearthToolsSQLiteOpenHelper;
import com.tcapps.hearthtools.database.dao.DeckDao;

import javax.inject.Singleton;

@Module(injects = {DeckDao.class}, library = true, complete = false)
public class DatabaseModule {

    private Context mContext;

    public DatabaseModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    HearthToolsSQLiteOpenHelper provideHearthToolsSQLiteOpenHelper() {
        return new HearthToolsSQLiteOpenHelper(mContext);
    }

    @Provides
    @Singleton
    DeckDao provideDeckDao(HearthToolsSQLiteOpenHelper openHelper) {
        return new DeckDao(openHelper);
    }
}
