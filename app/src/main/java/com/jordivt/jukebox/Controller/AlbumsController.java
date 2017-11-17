package com.jordivt.jukebox.Controller;

import com.jordivt.jukebox.Model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsController {

    private static final String COLLECTION = "collection";

    List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        List<Album> filteredAlbums = new ArrayList<>();

        for (Album album : albums) {
            if (album.getWrapperType().equals(COLLECTION)) {
                filteredAlbums.add(album);
            }
        }

        this.albums = filteredAlbums;
    }
}