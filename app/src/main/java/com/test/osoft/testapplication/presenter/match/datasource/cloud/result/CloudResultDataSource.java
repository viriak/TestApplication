package com.test.osoft.testapplication.presenter.match.datasource.cloud.result;

import android.util.Log;

import com.google.gson.Gson;
import com.test.osoft.testapplication._utils.UtilsContract;
import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CloudResultDataSource implements ICloudResultDataSource {

    private Retrofit retrofit;
    private static final String TAG = "RESULT_INCIDENCE";

    @Override
    public void getResults(final ResultsServiceCallback callback) {
        try {
            List<Fixture> fixtureList = new ArrayList<>();
            int totalOctosyllabicIndex=1;
            retrofit = new Retrofit.Builder()
                    .baseUrl(UtilsContract.urlAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ResultApiService service = retrofit.create(ResultApiService.class);
            Gson gson = new Gson();
            String fixtureJson = gson.toJson(fixtureList);
            Call<List<Result>> resultAnswerCall = service.getResult();

            resultAnswerCall.enqueue(new Callback<List<Result>>() {
                @Override
                public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                    //aptoParaCargar = true;
                    if (response.isSuccessful()) {

                        List<Result> resultAnswer = response.body();
                        callback.onLoaded(resultAnswer);
                        //txtDataInfo.setText("HOLA " + fixtureAnswer.get(0).getType());
                        //Toast.makeText(OctosyllabicImageActivity.this, "ID ANDROID: " +
                        //        userAnswer.get(0).getIDOrder(), Toast.LENGTH_LONG).show();
                        //ChangeImage(userAnswer);
                        //ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                        //listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Result>> call, Throwable t) {
                    if(call.isCanceled()) {
                        Log.e(TAG, "request was aborted");
                        //txtDataInfo.setText("FALLA RETROFIT CANCELACIÓN: " + t.getMessage());
                    }else {
                        Log.e(TAG, "Unable to submit post to API.");
                        //txtDataInfo.setText("FALLA RETROFIT SUBMIT: " + t.getMessage());
                    }
                    //showErrorMessage();
                    //Log.e(TAG, " onFailure: " + t.getMessage());
                    //txtUserName.setText("FALLA RETROFIT: " + t.getMessage());

                }


            });
        }
        catch(Exception ex)
        {
            String e = ex.toString();
            String d ="ERROR " + e;
            callback.onError(e);
            //txtDataInfo.setText("Error: " + ex.toString());
        }
    }
}
