package com.test.osoft.testapplication.presenter.match.datasource.cloud.result;

import com.test.osoft.testapplication.model.Result;

import java.util.List;

public interface ICloudResultDataSource {

    interface ResultsServiceCallback {
        void onLoaded(List<Result> Results);
        void onError(String error);
    }

    void getResults(ResultsServiceCallback callback);

}
