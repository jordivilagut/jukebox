package com.jordivt.jukebox.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jordivt.jukebox.Model.Album;
import com.jordivt.jukebox.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.CustomViewHolder> {

    private Context context;
    private List<Album> albums;


    public AlbumsAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false);
        return new CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.artist.setText(album.getArtistName());
        holder.name.setText(album.getCollectionName());
        holder.tracks.setText("16 tracks");
        Picasso.with(context).load(album.getArtworkUrl60()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        final TextView artist;
        final TextView name;
        final TextView tracks;
        final ImageView thumbnail;

        CustomViewHolder(View layout) {
            super(layout);
            artist = (TextView) layout.findViewById(R.id.artist_name);
            name = (TextView) layout.findViewById(R.id.album_name);
            tracks = (TextView) layout.findViewById(R.id.track_count);
            thumbnail = (ImageView) layout.findViewById(R.id.album_thumbnail);
        }
    }
}
