package com.test.osoft.testapplication.model;

import java.util.Date;

/**
 * Created by user on 24/06/2019.
 */

public class Fixture {
    public Fixture()
    {}
    public Fixture(int mId, String mType, String mState)
    {
        int id = mId;
        String type = mType;

        state = mState;

    }
        private int id;
        private String type;
        private Team homeTeam;
        private Team   awayTeam;
        private Date date;
        private CompetitionStage competitionStage;
        private Venue venue;
        private String state;

    public String getType() {
        return type;
    }
    public String getState() {
        return state;
    }


    public void setID(int ID) {
        this.id = ID;
    }
    public void setType(String Type) {
        this.type = Type;
    }
    public void setState(String State) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStage competitionStage) {
        this.competitionStage = competitionStage;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
