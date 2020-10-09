package com.example.photogallery;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photogallery.db.PhotosDB;
import com.example.photogallery.db.PhotosDao;
import com.example.photogallery.db.Repository;
import com.model.Example;
import com.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;


public class PhotoAdapter extends RecyclerView.Adapter <PhotoAdapter.ViewHolder>{
    private final Callback<Example> photoGallery;
    private final List<Photo> values;



    PhotoAdapter(Callback<Example> parent, List<Photo> items) {
        photoGallery = parent;
        values = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String s;
        s = "https://farm" + Integer.toString(values.get(position).getFarm()) + ".staticflickr.com/" +
                values.get(position).getServer() + "/" + values.get(position).getId() +
                "_" + values.get(position).getSecret() + "_q.jpg";
        Picasso.get().load(s).into(holder.idView);
        holder.itemView.setTag(values.get(position));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView idView;

        ViewHolder(View view) {
            super(view);
            idView = view.findViewById(R.id.iv);

        }
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Photo item = (Photo) view.getTag();

        }
    };
}
