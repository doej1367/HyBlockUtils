package com.doej.hyblockutils.page;

import android.widget.ProgressBar;

import com.doej.hyblockutils.main.MainActivity;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

/**
 *
 * @author doej1367
 */
public abstract class UpdatableFragment extends Fragment {
    protected enum Status {
        OK,
        INVALID_KEY,
        ERROR
    }
    protected ArrayList<String> queryWords;

    /**
     * a method that is executed in the WaitFragment Constructor and
     * is executed in a new thread and therefore internet requests are allowed in this method
     *
     * @param progressBar - progress bar to update after each loading step
     * @return - true in case the update was successful, false otherwise
     */
    public abstract Status updateData(ProgressBar progressBar);

    public int getTextSize(){
        return ((MainActivity) requireActivity()).getTextSize();
    }

    public void setQuery(String query) {
        queryWords = new ArrayList<>();
        StringBuilder queryWord = new StringBuilder();
        boolean locked = false;
        for (char c : query.toLowerCase().toCharArray()) {
            if (locked)
                if (c == '"') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                    locked = false;
                } else
                    queryWord.append(c);
            else {
                if (c == '"') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                    locked = true;
                } else if (c == ' ') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                } else
                    queryWord.append(c);
            }
        }
        if (queryWord.length() > 0)
            queryWords.add(queryWord.toString());
        if (queryWords.size() < 1)
            queryWords.add("");

    }
}
