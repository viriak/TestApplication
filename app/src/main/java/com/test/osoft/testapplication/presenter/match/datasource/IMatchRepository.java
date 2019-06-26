package com.test.osoft.testapplication.presenter.match.datasource;

import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;

import java.util.List;

public interface IMatchRepository {
    interface GetRepositoryFixturesCallback {
        void onFixturesLoaded(List<Fixture> fixtures);
        void onDataNotAvailable(String error);
    }

    interface GetRepositoryResultsCallback {
        void onResultsLoaded(List<Result> results);
        void onDataNotAvailable(String error);
    }
    void getFixtures(GetRepositoryFixturesCallback callback);
    void getResults(GetRepositoryResultsCallback callback);

}
