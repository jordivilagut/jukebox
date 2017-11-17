package com.jordivt.jukebox.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jordivt.jukebox.Adapter.AlbumsAdapter;
import com.jordivt.jukebox.Application.JukeboxApp;
import com.jordivt.jukebox.Controller.AlbumsController;
import com.jordivt.jukebox.Model.AlbumsDTO;
import com.jordivt.jukebox.R;
import com.jordivt.jukebox.Service.ApiService;
import com.jordivt.jukebox.Util.ApiUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private final static String JOHNSON_ID = "909253";
    private final static String TYPE_ALBUM = "album";

    private RecyclerView recyclerView;
    private AlbumsController albumsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        albumsController = new AlbumsController();
        initView();
        retrieveAlbums();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView = (RecyclerView) findViewById(R.id.albums_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
    }

    private void retrieveAlbums() {
        ApiService api = JukeboxApp.get().getApiService();
        Map<String, String> query = ApiUtil.getQueryMap(JOHNSON_ID, TYPE_ALBUM);
        Call<AlbumsDTO> call = api.getAlbums(query);

        call.enqueue(new Callback<AlbumsDTO>() {
            @Override
            public void onResponse(Call<AlbumsDTO> call, Response<AlbumsDTO> response) {
                if (response.isSuccessful()) {
                    albumsController.setAlbums(response.body().getAlbums());
                    displayAlbumsList();
                } else {

                }
            }

            @Override
            public void onFailure(Call<AlbumsDTO> call, Throwable t) {

            }
        });

    }

    private void displayAlbumsList() {
        AlbumsAdapter adapter = new AlbumsAdapter(getBaseContext(), albumsController.getAlbums());
        recyclerView.setAdapter(adapter);
    }
}
