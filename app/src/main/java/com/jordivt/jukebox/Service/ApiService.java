package com.jordivt.jukebox.Service;

import com.jordivt.jukebox.Model.AlbumsDTO;
import com.jordivt.jukebox.Model.TracksDTO;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("/lookup")
    Call<AlbumsDTO> getAlbums(
            @QueryMap Map<String,String> params);

    @GET("/lookup")
    Call<TracksDTO> getTracks(
            @QueryMap Map<String,String> params);
}
