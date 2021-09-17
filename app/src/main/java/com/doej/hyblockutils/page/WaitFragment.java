package com.doej.hyblockutils.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.doej.hyblockutils.main.MainActivity;
import com.doej.hyblockutils.R;

import androidx.fragment.app.Fragment;

/**
 *
 * @author doej1367
 */
public class WaitFragment extends Fragment {

    private final UpdatableFragment nextFragment;

    public WaitFragment(UpdatableFragment nextFragment) {
        this.nextFragment = nextFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait, container, false);
        ImageView img = view.findViewById(R.id.wait);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        MainActivity.setFetcherThread(new Thread() {
            @Override
            public void run() {
                try {
                    if (nextFragment.updateData(progressBar) == UpdatableFragment.Status.OK)
                        requireActivity().runOnUiThread(() -> requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, nextFragment)
                                .commitNow());
                    else if (nextFragment.updateData(progressBar) == UpdatableFragment.Status.INVALID_KEY)
                        requireActivity().runOnUiThread(() -> img.setImageResource(R.drawable.ic_baseline_vpn_key_24));
                    else
                        requireActivity().runOnUiThread(() -> img.setImageResource(R.drawable.ic_baseline_close_64));
                } catch (Exception ignore) {
                    // this thread does not like being interrupted...
                    // but it has to be for the "back" operation - that's what this "catch" is for
                    // to catch all the errors that interrupting e.g. IO operations causes
                }
            }
        });
        return view;
    }
}