package com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture;

import com.test.osoft.testapplication.model.Fixture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 24/06/2019.
 */

public interface FixtureApiService {
    @GET("fixtures.json")
        //Call<ProductNutritionalValue> getData();
    Call<List<Fixture>> getFixture();

}
