package com.doej.hyblockutils.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.page.BazaarFragment;
import com.doej.hyblockutils.page.CalenderFragment;
import com.doej.hyblockutils.page.NotificationFragment;
import com.doej.hyblockutils.page.PriceLookupFragment;
import com.doej.hyblockutils.page.ProfileFragment;
import com.doej.hyblockutils.page.ProfileSearchFragment;
import com.doej.hyblockutils.page.SearchFragment;
import com.doej.hyblockutils.page.WaitFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

/**
 *
 * @author doej1367
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private final MainActivity mainActivity;
    private final Fragment[] frags = {
            new WaitFragment(new ProfileFragment()),
            new SearchFragment(new ProfileSearchFragment(), "enter player name ..."),
            new CalenderFragment(),
            new NotificationFragment(),
            new WaitFragment(new BazaarFragment()),
            new SearchFragment(new PriceLookupFragment(), "search for an item ...")
    };

    public MainFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        view.findViewById(R.id.myProfile).setOnClickListener(this);
        view.findViewById(R.id.profileSearch).setOnClickListener(this);
        view.findViewById(R.id.calender).setOnClickListener(this);
        view.findViewById(R.id.notifications).setOnClickListener(this);
        view.findViewById(R.id.bazaar).setOnClickListener(this);
        view.findViewById(R.id.priceLookup).setOnClickListener(this);
        // TODO protect and encrypt saved api key
        MainActivity.setApiKey(PreferenceManager.getDefaultSharedPreferences(requireActivity()).getString("api_key_preference", ""));
        MainActivity.setName(PreferenceManager.getDefaultSharedPreferences(requireActivity()).getString("name_preference", ""));
        MainActivity.setLimitRequests(PreferenceManager.getDefaultSharedPreferences(requireActivity()).getBoolean("limit_page_requests", true));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myProfile:
                switchFragment(frags[0]);
                break;
            case R.id.profileSearch:
                switchFragment(frags[1]);
                break;
            case R.id.calender:
                switchFragment(frags[2]);
                break;
            case R.id.notifications:
                switchFragment(frags[3]);
                break;
            case R.id.bazaar:
                switchFragment(frags[4]);
                break;
            case R.id.priceLookup:
                switchFragment(frags[5]);
                break;
        }
    }

    private void switchFragment(Fragment frag) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .commitNow();
        mainActivity.showBackArrow();
    }
}