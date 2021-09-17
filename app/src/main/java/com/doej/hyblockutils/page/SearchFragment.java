package com.doej.hyblockutils.page;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.doej.hyblockutils.main.MainActivity;
import com.doej.hyblockutils.R;

import androidx.fragment.app.Fragment;

/**
 *
 * @author doej1367
 */
public class SearchFragment extends Fragment {

    private final UpdatableFragment nextFragment;
    private final String hint;

    public SearchFragment(UpdatableFragment nextFragment, String hint) {
        this.nextFragment = nextFragment;
        this.hint = hint;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        EditText textInput = view.findViewById(R.id.searchQuery);
        textInput.setHint(hint);
        textInput.setTextSize(((MainActivity) requireActivity()).getTextSize());
        textInput.setOnKeyListener((view1, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                switch (i) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        nextFragment.setQuery(textInput.getText().toString());
                        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
                        switchFragment(new WaitFragment(nextFragment));
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });
        return view;
    }

    private void switchFragment(Fragment frag) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .commitNow();
    }
}