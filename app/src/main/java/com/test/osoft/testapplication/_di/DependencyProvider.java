package com.test.osoft.testapplication._di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.test.osoft.testapplication.presenter.match.datasource.MatchRepository;
import com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture.CloudFixtureDataSource;
import com.test.osoft.testapplication.presenter.match.datasource.cloud.result.CloudResultDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class DependencyProvider {
    private static Context mContext;
    private static CloudFixtureDataSource cloudFixtureSource = null;
    private static CloudResultDataSource cloudResultSource = null;
    private static MatchRepository mMatchesRepository = null;
    private DependencyProvider() {
    }
    public static MatchRepository provideMatchesRepository(@NonNull Context
                                                                   context) {
        mContext = checkNotNull(context);
        if (mMatchesRepository == null) {
            mMatchesRepository = new MatchRepository(
                    getCloudFixtureSource(),getCloudResultSource(), context);
        }
        return mMatchesRepository;
    }

    public static CloudFixtureDataSource getCloudFixtureSource() {
        if (cloudFixtureSource == null) {
            cloudFixtureSource = new CloudFixtureDataSource();
        }
        return cloudFixtureSource;
    }

    public static CloudResultDataSource getCloudResultSource() {
        if (cloudResultSource == null) {
            cloudResultSource = new CloudResultDataSource();
        }
        return cloudResultSource;
    }
}
