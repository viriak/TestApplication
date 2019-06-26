package com.test.osoft.testapplication.presenter.match.datasource.cloud.fixture;

import com.test.osoft.testapplication.model.Fixture;

import java.util.List;

public interface ICloudFixtureDataSource {

        interface FixturesServiceCallback {
            void onLoaded(List<Fixture> Fixtures);
            void onError(String error);
        }

        void getFixtures(FixturesServiceCallback callback);

}
