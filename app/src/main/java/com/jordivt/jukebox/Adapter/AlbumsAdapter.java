package com.jordivt.jukebox.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jordivt.jukebox.Application.JukeboxApp;
import com.jordivt.jukebox.Controller.AlbumsController;
import com.jordivt.jukebox.Controller.ControllerFactory;
import com.jordivt.jukebox.Model.Album;
import com.jordivt.jukebox.Model.Track;
import com.jordivt.jukebox.Model.TracksDTO;
import com.jordivt.jukebox.R;
import com.jordivt.jukebox.Service.ApiService;
import com.jordivt.jukebox.Util.ApiUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.CustomViewHolder> {

    private final static String TYPE_TRACK = "song";

    private Context context;
    private List<Album> albums;
    private AlbumsController albumsController;

    public AlbumsAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
        this.albumsController = ControllerFactory.get().getAlbumsController();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false);
        return new CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        final Album album = albums.get(position);
        holder.artist.setText(album.getArtistName());
        holder.name.setText(album.getCollectionName());
        holder.tracks.setText(context.getString(R.string.tracks, album.getTrackCount()));
        holder.tracklist.setVisibility(album.isExpandedView() ? View.VISIBLE : View.GONE);
        Picasso.with(context).load(album.getArtworkUrl60()).fit().into(holder.thumbnail);
        retrieveTracks(holder.tracklist, String.valueOf(album.getCollectionId()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = album.isExpandedView()? View.GONE : View.VISIBLE;
                holder.tracklist.setVisibility(visibility);
                album.setExpandedView(!album.isExpandedView());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        final View layout;
        final TextView artist;
        final TextView name;
        final TextView tracks;
        final ImageView thumbnail;
        final RecyclerView tracklist;

        CustomViewHolder(View layout) {
            super(layout);
            this.layout = layout;
            artist = (TextView) layout.findViewById(R.id.artist_name);
            name = (TextView) layout.findViewById(R.id.album_name);
            tracks = (TextView) layout.findViewById(R.id.track_count);
            thumbnail = (ImageView) layout.findViewById(R.id.album_thumbnail);
            tracklist = (RecyclerView) layout.findViewById(R.id.tracklist);
        }
    }

    private void retrieveTracks(final RecyclerView tracksRecyclerview, final String albumId) {
        if (albumsController.getAlbumById(albumId).getTrackList() == null) {
            ApiService api = JukeboxApp.get().getApiService();
            Call<TracksDTO> call = api.getTracks(ApiUtil.getQueryMap(albumId, TYPE_TRACK));

            call.enqueue(new Callback<TracksDTO>() {
                @Override
                public void onResponse(Call<TracksDTO> call, Response<TracksDTO> response) {
                    if (response.isSuccessful()) {
                        List<Track> trackList = response.body().getTracks();
                        albumsController.addTracklistToAlbum(albumId, trackList);
                        addTracksToRecyclerview(tracksRecyclerview, albumId);
                    } else {
                        new android.support.v7.app.AlertDialog.Builder(context)
                                .setMessage(ApiUtil.parseErrorMessage(context, response))
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<TracksDTO> call, Throwable t) {
                    ApiUtil.displayServerError(context);
                }
            });
        } else {
            addTracksToRecyclerview(tracksRecyclerview, albumId);
        }
    }

    private void addTracksToRecyclerview(RecyclerView tracksRecyclerview, String albumId) {
        tracksRecyclerview.setLayoutManager( new LinearLayoutManager(context));
        tracksRecyclerview.setAdapter(new TracksAdapter(albumsController.getAlbumById(albumId).getTrackList()));
    }
}
