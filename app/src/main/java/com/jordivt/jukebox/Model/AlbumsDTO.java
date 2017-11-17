package com.jordivt.jukebox.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumsDTO {

    @SerializedName("resultsCount")
    private int count;
    @SerializedName("results")
    private List<Album> albums;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
