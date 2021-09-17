package com.doej.hyblockutils.page;

import android.os.Bundle;

import com.doej.hyblockutils.R;

import androidx.preference.PreferenceFragmentCompat;

/**
 *
 * @author doej1367
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}