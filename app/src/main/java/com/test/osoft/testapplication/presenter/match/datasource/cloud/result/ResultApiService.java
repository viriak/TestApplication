package com.test.osoft.testapplication.presenter.match.datasource.cloud.result;

import com.test.osoft.testapplication.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResultApiService {
    @GET("results.json")
        //Call<ProductNutritionalValue> getData();
    Call<List<Result>> getResult();

}
