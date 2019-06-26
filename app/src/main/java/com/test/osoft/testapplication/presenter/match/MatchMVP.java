package com.test.osoft.testapplication.presenter.match;

import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;

import java.util.List;

/**
 * Created by user on 24/06/2019.
 */

public class MatchMVP {

    interface View {
        void showFixture(List<Fixture> fixtures);
        void showResult(List<Result> results);

        void showLoadingState(boolean show);

        void showError(String error);
    }
        interface Presenter {
        void loadFixture();
        void loadResult();

    }
}
