package ca.talkad.wildlifeclock.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import ca.talkad.wildlifeclock.alarm.Alarm;
import ca.talkad.wildlifeclock.db.DatabaseManager;
import ca.talkad.wildlifeclock.fragments.AlarmFragment;

/**
 * Created by mariu on 10.12.2016.
 */

public class MyItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private AlarmFragment alarmFragment;

    public MyItemTouchHelper(int dragDirs, int swipeDirs, AlarmFragment alarmFragment) {
        super(dragDirs, swipeDirs);
        this.alarmFragment = alarmFragment;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final Alarm alarm = (Alarm) alarmFragment.getAdapter().getItem(viewHolder.getAdapterPosition());

        if (alarm != null) {
            alarm.cancelAlarm(alarmFragment.getContext());
            DatabaseManager.deleteEntry(alarm);
        }

        alarmFragment.updateAlarmList();
    }

}
