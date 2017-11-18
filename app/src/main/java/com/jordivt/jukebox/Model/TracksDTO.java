package com.jordivt.jukebox.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TracksDTO {

    @SerializedName("resultsCount")
    private int count;
    @SerializedName("results")
    private List<Track> tracks;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}