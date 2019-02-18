package ca.talkad.wildlifeclock.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import ca.talkad.wildlifeclock.alarm.Alarm;

/**
 * WakefulBroadcastReceiver gets the broadcast Intent 
 * and then starts the scheduling service to do some work.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {

    private final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: " + intent.toString());

        final Bundle bundle = intent.getExtras();
        final Intent serviceIntent = new Intent(context, SchedulingService.class);

        serviceIntent.putExtra(Alarm.TAG, bundle.getByteArray(Alarm.TAG));
        serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, serviceIntent);
    }
}
