package com.test.osoft.testapplication.presenter.match.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;
import com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture.ICloudFixtureDataSource;
import com.test.osoft.testapplication.presenter.match.datasource.cloud.result.ICloudResultDataSource;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by user on 24/06/2019.
 */

public class MatchRepository implements IMatchRepository {

    private final ICloudFixtureDataSource mCloudFixturesDataSource;
    private final ICloudResultDataSource mCloudResultsDataSource;
    private final Context mContext;
    private boolean mReload = true;



    public MatchRepository(ICloudFixtureDataSource cloudFixtureDataSource, ICloudResultDataSource cloudResultDataSource,
                          Context context) {
        mCloudFixturesDataSource = checkNotNull(cloudFixtureDataSource);
        mCloudResultsDataSource= checkNotNull(cloudResultDataSource);
        mContext = checkNotNull(context);
    }



    @Override
    public void getFixtures(GetRepositoryFixturesCallback callback) {
        getFixtureFromServer(callback);
    }

    @Override
    public void getResults(GetRepositoryResultsCallback callback) {
        getResultFromServer(callback);
    }

    private void getFixtureFromServer(final GetRepositoryFixturesCallback callback) {
        if (!isOnline()) {
            callback.onDataNotAvailable("No hay conexión de red.");
            return;
        }
        mCloudFixturesDataSource.getFixtures(
                new ICloudFixtureDataSource.FixturesServiceCallback() {
                    @Override
                    public void onLoaded(List<Fixture> fixtures) {
                        callback.onFixturesLoaded(fixtures);

                    }
                    @Override
                    public void onError(String error) {
                        callback.onDataNotAvailable(error);
                    }
                });
    }

    private void getResultFromServer(final GetRepositoryResultsCallback callback) {
        if (!isOnline()) {
            callback.onDataNotAvailable("No hay conexión de red.");
            return;
        }
        mCloudResultsDataSource.getResults(
                new ICloudResultDataSource.ResultsServiceCallback() {
                    @Override
                    public void onLoaded(List<Result> results) {
                        callback.onResultsLoaded(results);

                    }
                    @Override
                    public void onError(String error) {
                        callback.onDataNotAvailable(error);
                    }
                });
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
