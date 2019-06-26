package com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture;

import com.test.osoft.testapplication.model.Fixture;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 24/06/2019.
 */

public interface FixtureRestClient {
    @GET("/get")
    Call<Fixture> getData();

}
