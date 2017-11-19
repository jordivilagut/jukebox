package com.jordivt.jukebox.Controller;

import com.jordivt.jukebox.Model.Album;
import com.jordivt.jukebox.Model.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumsController {

    private static final String COLLECTION = "collection";
    private static final String TRACK = "track";


    List<Album> albums;
    Map<String,Album> albumsMap;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albumList) {
        this.albums = getFilteredAlbumsList(albumList);
        this.albumsMap = getFilteredAlbums(albumList);
    }

    public Album getAlbumById(String id) {
        return albumsMap.get(id);
    }

    public void addTracklistToAlbum(String albumId, List<Track> trackList) {
        Album album = getAlbumById(albumId);
        album.setTrackList(getFilteredTrackList(trackList));
        albumsMap.put(albumId, album);
    }

    private List<Album> getFilteredAlbumsList(List<Album> albumList) {
        List<Album> filtered = new ArrayList<>();
        for (Album album : albumList) {
            if (album.getWrapperType().equals(COLLECTION)) filtered.add(album);
        }
        return filtered;
    }

    private Map<String,Album> getFilteredAlbums(List<Album> albumList) {
        Map<String,Album> filtered = new HashMap<>();
        for (Album album : albumList) {
            if (album.getWrapperType().equals(COLLECTION)) filtered.put(String.valueOf(album.getCollectionId()), album);
        }
        return filtered;
    }

    private List<Track> getFilteredTrackList(List<Track> trackList) {
        List<Track> filtered = new ArrayList<>();
        for (Track track : trackList) {
            if (track.getWrapperType().equals(TRACK)) filtered.add(track);
        }
        return filtered;
    }
}