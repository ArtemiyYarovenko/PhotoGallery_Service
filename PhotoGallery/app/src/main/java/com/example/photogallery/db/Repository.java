package com.example.photogallery.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.model.Photo;

import java.util.List;

public class Repository {
    private String DB_NAME = "db_task";

    private PhotosDB db;
    public Repository(Context context) {
        db = Room.databaseBuilder(context, PhotosDB.class, DB_NAME).build();
    }


    public void insertPhoto(final Photo photo) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.photoDao().insertPhoto(photo);
                return null;
            }
        }.execute();
    }



    public void deleteTask(final Photo photo) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.photoDao().deletePhoto(photo);
                return null;
            }
        }.execute();
    }


    public List<Photo> LoadAll() {
        return db.photoDao().LoadAll();
    }
}

