package com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture;

import android.util.Log;

import com.google.gson.Gson;
import com.test.osoft.testapplication._utils.UtilsContract;
import com.test.osoft.testapplication.model.Fixture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 24/06/2019.
 */

public class CloudFixtureDataSource implements ICloudFixtureDataSource {

    private Retrofit retrofit;
    private static final String TAG = "FIXTURE_INCIDENCE";
    private static final long LATENCY = 2000;

    @Override
    public void getFixtures(final FixturesServiceCallback callback) {

            try {
                List<Fixture> fixtureList = new ArrayList<>();
                int totalOctosyllabicIndex=1;
                retrofit = new Retrofit.Builder()
                        .baseUrl(UtilsContract.urlAPI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FixtureApiService service = retrofit.create(FixtureApiService.class);
                Gson gson = new Gson();
                String fixtureJson = gson.toJson(fixtureList);
                Call<List<Fixture>> fixtureAnswerCall = service.getFixture();

                fixtureAnswerCall.enqueue(new Callback<List<Fixture>>() {
                    @Override
                    public void onResponse(Call<List<Fixture>> call, Response<List<Fixture>> response) {
                        //aptoParaCargar = true;
                        if (response.isSuccessful()) {

                            List<Fixture> fixtureAnswer = response.body();
                            callback.onLoaded(fixtureAnswer);
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
                    public void onFailure(Call<List<Fixture>> call, Throwable t) {
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

    private static HashMap<String, Fixture> API_DATA;
    static {
        API_DATA = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            addFixture(43, "Trabajo " + (i + 1),
                    "file:///android_asset/suitcase.png");
        }
    }
    private static void addFixture(int id, String type, String state) {
        Fixture newFixture = new Fixture(id, type, state);
        API_DATA.put(newFixture.getType(), newFixture);
    }


}
