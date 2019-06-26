package com.test.osoft.testapplication.presenter.match;

import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;
import com.test.osoft.testapplication.presenter.match.datasource.MatchRepository;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by user on 24/06/2019.
 */

public class MatchPresenter implements MatchMVP.Presenter{

    private final MatchRepository mMatchRepository;
    private final MatchMVP.View mMatchView;

    public MatchPresenter(MatchRepository productsRepository,
                         MatchMVP.View productsView) {
        mMatchRepository = checkNotNull(productsRepository);
        mMatchView = checkNotNull(productsView);


    }

    @Override
    public void loadFixture() {
        mMatchRepository.getFixtures( new MatchRepository.GetRepositoryFixturesCallback(){


            @Override
            public void onFixturesLoaded(List<Fixture> fixtures) {
                mMatchView.showLoadingState(false);
                mMatchView.showFixture(fixtures);
            }

            @Override
            public void onDataNotAvailable(String error) {
                mMatchView.showLoadingState(false);
                mMatchView.showError(error);
            }
        });
    }

    @Override
    public void loadResult() {
        mMatchRepository.getResults( new MatchRepository.GetRepositoryResultsCallback(){


            @Override
            public void onResultsLoaded(List<Result> results) {
                mMatchView.showLoadingState(false);
                mMatchView.showResult(results);
            }

            @Override
            public void onDataNotAvailable(String error) {
                mMatchView.showLoadingState(false);
                mMatchView.showError(error);
            }
        });
    }
}
