package com.doej.hyblockutils.page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.doej.hyblockutils.R;

/**
 *
 * @author doej1367
 */
public class CalenderFragment extends Fragment {

    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        FrameLayout frame = view.findViewById(R.id.frame);
        frame.addView(new CalenderView(getActivity()));
        return view;
    }
}