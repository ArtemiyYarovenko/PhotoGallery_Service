package com.example.photogallery;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.util.Log;
import com.example.photogallery.api.FetchItemTask;
import com.example.photogallery.api.FlickrFetch;
import com.model.Example;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PollService extends IntentService {
    private static final String TAG = "PollService";
    private static final long POLL_INTERVAL_MS = TimeUnit.SECONDS.toMillis(10);
    Retrofit r = FetchItemTask.getRetrofit();

    public PollService() {
        super(TAG);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PollService.class);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent intent = PollService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);
        if (!isOn) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), POLL_INTERVAL_MS, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent intent = PollService.newIntent(context);
        PendingIntent pi = PendingIntent
                .getService(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        if (!isNetworkAvailableAndConnected()) {
            Log.i(TAG, "Нет соединения: " + intent);
            return;
        }

        Log.i(TAG, "Служба запущена ff: " + intent);

        // так как в нашей версии PhotoGallery не было QueryPreferences пришлось как-то догадаться
        // и сымитировать работу, которая подразумевается в лабораторной.
        final String last_res_id = QueryPreferences.getLastResultId(this);
        String stored_query = QueryPreferences.getStoredQuery(this);
        r.create(FlickrFetch.class).getRecent().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response != null){
                String first_photo_id = response.body().getPhotos().getPhoto().get(0).getId();
                if (first_photo_id != last_res_id) {
                    QueryPreferences.setLastResultId(getApplicationContext(), first_photo_id);
                    QueryPreferences.setStoredQuery(getApplicationContext(),response.toString());
                }}

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo nwInfo = cm.getActiveNetworkInfo();
        return nwInfo != null && nwInfo.isConnected();

    }

}
