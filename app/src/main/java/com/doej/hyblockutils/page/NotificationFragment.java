package com.doej.hyblockutils.page;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.util.AlarmReceiver;
import com.doej.hyblockutils.util.HyBlockEvent;
import com.doej.hyblockutils.util.HyBlockTime;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

/**
 *
 * @author doej1367
 */
public class NotificationFragment extends Fragment {
    private AlarmManager alarmManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        addNotificationSwitch(new HyBlockEvent("DARK AUCTION", 0, new HyBlockTime(0,0,0,0), 0), linearLayout);
        for (HyBlockEvent event : HyBlockEvent.SINGLE_NEXT_EVENTS)
            addNotificationSwitch(event, linearLayout);
        return view;
    }

    private void addNotificationSwitch(HyBlockEvent event, LinearLayout linearLayout) {
        // TODO this notification system does not cover all the edge cases
        // weaknesses: no alarm-reactivation on system-reboot, false alarms on manually adjusted system-time
        // maybe something like this https://learntodroid.com/how-to-create-a-simple-alarm-clock-app-in-android/
        Intent intent = new Intent(requireActivity(), AlarmReceiver.class)
                .putExtra("title", event.getName())
                .putExtra("message", "" + event.getNextEventOccurrence());
        PendingIntent oldAlarm = PendingIntent.getBroadcast(requireActivity(), event.getID(), intent, PendingIntent.FLAG_NO_CREATE);
        boolean alarmExists = oldAlarm != null;
        SwitchCompat s = new SwitchCompat(requireActivity());
        s.setText(event.getName());
        s.setChecked(alarmExists);
        s.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(requireActivity(), event.getID(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
            if (pendingIntent == null)
                return;
            if (isChecked) {
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + event.getSecondsToNextOccurrence() * 1000,
                        pendingIntent);
            } else {
                alarmManager.cancel(pendingIntent);
                pendingIntent.cancel();
            }
        });
        linearLayout.addView(s);
    }
}