package com.jordivt.jukebox.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jordivt.jukebox.Model.Track;
import com.jordivt.jukebox.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.CustomViewHolder> {


    private List<Track> trackList;

    public TracksAdapter(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_track, parent, false);
        return new TracksAdapter.CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Track track = trackList.get(position);
        holder.name.setText(track.getTrackName());
        holder.duration.setText(getFormattedDuration(track.getTrackTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView duration;

        CustomViewHolder(View layout) {
            super(layout);
            name = (TextView) layout.findViewById(R.id.track_name);
            duration = (TextView) layout.findViewById(R.id.track_duration);
        }
    }

    private String getFormattedDuration(long millis) {
        return String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}
