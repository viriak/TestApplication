package com.test.osoft.testapplication.presenter.match.presentation._adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.osoft.testapplication.R;
import com.test.osoft.testapplication.model.Result;
import com.test.osoft.testapplication.presenter.match.presentation.MatchAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ResultAdapter  extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private List<Fixture> mFixtures;
    private List<Result> mResults;
    private ResultItemListener mItemListener;
    int mScreen_match=-1;
    Context context;


    public ResultAdapter(List<Result> results, ResultAdapter.ResultItemListener
            itemListener, int screen_match) {
        setList(results);
        mItemListener = itemListener;
        mScreen_match = screen_match;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(mScreen_match==0)
            view = inflater.inflate(R.layout.item_fixture, parent, false);
        else
            view = inflater.inflate(R.layout.item_result, parent, false);
        return new ResultAdapter.ResultsHolder(view, mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int
            position) {
        if (viewHolder instanceof ResultAdapter.ResultsHolder) {
            Result result = mResults.get(position);
            ResultAdapter.ResultsHolder matchHolder = (ResultAdapter.ResultsHolder) viewHolder;
            matchHolder.match_league.setText(result.getHomeTeam().getName());
            matchHolder.match_venue.setText(result.getVenue().getName().toUpperCase());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
            sdf.applyPattern("MMM d, yyyy HH:mm");
            String sMyDate = sdf.format(result.getDate());
            matchHolder.match_date.setText(sMyDate);


            matchHolder.match_home_team.setText(result.getHomeTeam().getName());
            matchHolder.match_away_team.setText(result.getAwayTeam().getName());
            matchHolder.match_league.setText(result.getCompetitionStage().getCompetition().getName());
            matchHolder.match_home_score.setText(String.valueOf(result.getScore().getHome()));
            matchHolder.match_away_score.setText(String.valueOf(result.getScore().getAway()));
            if(mScreen_match==1) {
                if (result!=null && result.getScore()!=null && result.getScore().getWinner().equals("home")) {
                    matchHolder.match_home_score.setTextColor(ContextCompat.getColor(context, R.color.colorWinner));
                } else {
                    if (result.getScore()!=null && result.getScore().getWinner().equals("away"))
                        matchHolder.match_away_score.setTextColor(ContextCompat.getColor(context, R.color.colorWinner));
                }
            }

        }
    }

    public void replaceData(List<Result> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<Result> notes) {
        mResults = checkNotNull(notes);
    }

    public void addData(List<Result> results) {
        mResults.addAll(results);
    }

    @Override
    public int getItemCount() {
        return getDataItemCount();
    }

    public Result getItem(int position) {
        return mResults.get(position);
    }

    public int getDataItemCount() {
        return mResults.size();
    }

    public class ResultsHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public TextView match_league;
        public TextView match_venue;
        public TextView match_date;
        public TextView match_state;

        public TextView match_home_team;
        public TextView match_away_team;
        public TextView match_home_score;
        public TextView match_away_score;
        private ResultItemListener mItemListener;

        public ResultsHolder(View itemView, ResultItemListener listener) {
            super(itemView);
            mItemListener = listener;

            match_league  = (TextView) itemView.findViewById(R.id.match_league);
            match_venue = (TextView) itemView.findViewById(R.id.match_venue);
            match_date = (TextView) itemView.findViewById(R.id.match_date);
            match_state = (TextView) itemView.findViewById(R.id.match_state);

            match_home_team = (TextView) itemView.findViewById(R.id.match_local_team);
            match_away_team = (TextView) itemView.findViewById(R.id.match_away_team);
            match_home_score = (TextView) itemView.findViewById(R.id.match_home_score);
            match_away_score = (TextView) itemView.findViewById(R.id.match_away_score);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //Fixture fixture = getItem(position);
            //mItemListener.onFixtureClick(fixture);
        }
    }

    public interface ResultItemListener {
        void onResultClick(Result clickedNote);
    }
}

