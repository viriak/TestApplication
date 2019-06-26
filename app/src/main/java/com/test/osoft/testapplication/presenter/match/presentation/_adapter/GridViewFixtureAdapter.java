package com.test.osoft.testapplication.presenter.match.presentation._adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.osoft.testapplication.R;
import com.test.osoft.testapplication.model.Fixture;

import java.util.ArrayList;

public class GridViewFixtureAdapter extends ArrayAdapter<Fixture> {

    public ArrayList<Fixture> imageItemList;
    private Context mContext = null;
    Integer selected_position = -1;

    public GridViewFixtureAdapter(Context context, int textViewResourceId,
                                  ArrayList<Fixture> countryList, int screen_match) {
        super(context, textViewResourceId, countryList);
        this.imageItemList = new ArrayList<Fixture>();
        this.imageItemList.addAll(countryList);
        mContext = context;
        selected_position = screen_match;
    }

    private class ViewHolder {
        TextView match_league;
        TextView local_team;
        TextView away_team;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            /*convertView = vi.inflate(R.layout.grid_item_vehicle_info, null);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
            holder.image = (ImageView)convertView.findViewById(R.id.image);*/
            if(selected_position.equals(1))
                convertView = vi.inflate(R.layout.grid_item_fixture, null);
            else
                convertView = vi.inflate(R.layout.grid_item_result, null);
            holder = new ViewHolder();
            holder.match_league = (TextView) convertView.findViewById(R.id.match_league);
            holder.local_team = (TextView) convertView.findViewById(R.id.match_local_team);
            holder.away_team = (TextView) convertView.findViewById(R.id.match_away_team);
            //holder.name = (CheckBox) convertView.findViewById(R.id.checkBox_info);
            //holder.image = (ImageView) convertView.findViewById(R.id.image_info);
            convertView.setTag(holder);

            /*holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //SelectedVehicle(position);
                    //CheckVehicle(position, v);
                }
            });

            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SelectedVehicle(position);
                    CheckProduct(v);
                }
            });*/
        } else {
            holder = (ViewHolder)/*(ListViewCheckBoxesActivity.MyCustomAdapter.ViewHolder)*/ convertView.getTag();
        }

        Fixture fixture = imageItemList.get(position);
        //holder.code.setText(" (" +  country.getTitle() + ")");
        holder.match_league.setText(fixture.getCompetitionStage().getCompetition().getName());
        holder.local_team.setText(fixture.getHomeTeam().getShortName());
        holder.away_team.setText(fixture.getAwayTeam().getShortName());
        //holder.name.setText(country.getTitle());
        //holder.name.setChecked(imageItem.isSelected());
        //holder.name.setTag(imageItem);
        //holder.image.setImageBitmap(imageItem.getImage());
        return convertView;

    }

    private void SelectedVehicle(int position) {
        /*for (int i = 0; i < imageItemList.size(); i++) {
            /CheckBox cb = (CheckBox) v;
            int id = cb.getId();+/
            if (i != position) {
                //cb.setChecked(false);
                imageItemList.get(i).setSelected(false);
                //arrayChecked[id] = false;
            } else {
                //cb.setChecked(true);
                //imageItemList.get(i).setSelected(true);
                //arrayChecked[id] = true;
            }
        }*/
    }

    private void CheckProduct(View v) {
        CheckBox cb = (CheckBox) v;
        //ImageItem imageItem = (ImageItem) cb.getTag();
        Toast.makeText(mContext.getApplicationContext(),
                "Clicked on Checkbox: " + cb.getText() +
                        " is " + cb.isChecked(),
                Toast.LENGTH_LONG).show();
        //imageItem.setSelected(cb.isChecked());
        notifyDataSetChanged();
    }
}



