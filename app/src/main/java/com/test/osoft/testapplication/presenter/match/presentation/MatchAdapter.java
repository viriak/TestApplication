package com.test.osoft.testapplication.presenter.match.presentation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.osoft.testapplication.R;
import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by user on 24/06/2019.
 */

public class MatchAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Fixture> mFixtures;
    private List<Result> mResults;
    private FixtureItemListener mItemListener;
    int mScreen_match=-1;
    Context context;
    public MatchAdapter(List<Fixture> fixtures, FixtureItemListener
            itemListener, int screen_match) {
        setList(fixtures);
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
        return new FixturesHolder(view, mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int
            position) {
        if (viewHolder instanceof FixturesHolder) {
            Fixture fixture = mFixtures.get(position);
            FixturesHolder matchHolder = (FixturesHolder) viewHolder;
            matchHolder.match_league.setText(fixture.getHomeTeam().getName());
            matchHolder.match_venue.setText(fixture.getVenue().getName().toUpperCase());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
            sdf.applyPattern("MMM d, yyyy HH:mm");
            String sMyDate = sdf.format(fixture.getDate());
            matchHolder.match_date.setText(sMyDate);

            if(mScreen_match==0) {
                if (fixture.getState()!=null && fixture.getState().equals("postponed")) {
                    matchHolder.match_state.setText(fixture.getState().toUpperCase());
                    matchHolder.match_state.setVisibility(View.VISIBLE);
                    matchHolder.match_date.setTextColor(ContextCompat.getColor(context, R.color.colorAlert));
                } else {
                    matchHolder.match_state.setVisibility(View.INVISIBLE);
                    matchHolder.match_date.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

                }
            }
            matchHolder.match_local_team.setText(fixture.getHomeTeam().getName());
            matchHolder.match_away_team.setText(fixture.getAwayTeam().getName());
            matchHolder.match_league.setText(fixture.getCompetitionStage().getCompetition().getName());
            SimpleDateFormat format1=new SimpleDateFormat("dd");
            String dayNumber = format1.format(fixture.getDate());
            matchHolder.match_day.setText(dayNumber);
            DateFormat format2=new SimpleDateFormat("EEE", java.util.Locale.ENGLISH);
            String finalDay=format2.format(fixture.getDate());
            matchHolder.match_day_of_the_week.setText(finalDay.toUpperCase());
        }
    }

    public void replaceData(List<Fixture> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<Fixture> notes) {
        mFixtures = checkNotNull(notes);
    }

    public void addData(List<Fixture> fixtures) {
        mFixtures.addAll(fixtures);
    }

    @Override
    public int getItemCount() {
        return getDataItemCount();
    }

    public Fixture getItem(int position) {
        return mFixtures.get(position);
    }

    public int getDataItemCount() {
        return mFixtures.size();
    }

    public class FixturesHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public TextView match_league;
        public TextView match_venue;
        public TextView match_date;
        public TextView match_state;

        public TextView match_local_team;
        public TextView match_away_team;
        public TextView match_day;
        public TextView match_day_of_the_week;
        public TextView match_home_score;
        public TextView match_away_score;

        private FixtureItemListener mItemListener;

        public FixturesHolder(View itemView, FixtureItemListener listener) {
            super(itemView);
            mItemListener = listener;

            match_league  = (TextView) itemView.findViewById(R.id.match_league);
            match_venue = (TextView) itemView.findViewById(R.id.match_venue);
            match_date = (TextView) itemView.findViewById(R.id.match_date);
            match_state = (TextView) itemView.findViewById(R.id.match_state);

            match_local_team = (TextView) itemView.findViewById(R.id.match_local_team);
            match_away_team = (TextView) itemView.findViewById(R.id.match_away_team);
            match_day = (TextView) itemView.findViewById(R.id.match_day);
            match_day_of_the_week = (TextView) itemView.findViewById(R.id.match_day_of_the_week);
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

    public interface FixtureItemListener {
        void onFixtureClick(Fixture clickedNote);
    }
}
