package com.doej.hyblockutils.page;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doej.hyblockutils.item.BazaarItem;

import java.util.List;

import com.doej.hyblockutils.R;

import org.jetbrains.annotations.NotNull;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.doej.hyblockutils.item.BazaarItem}.
 *
 * @author doej1367
 */
public class BazaarListViewAdapter extends RecyclerView.Adapter<BazaarListViewAdapter.ViewHolder> {

    private final List<BazaarItem> mValues;

    public BazaarListViewAdapter(List<BazaarItem> items) {
        mValues = items;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bazaar, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLetterView.setText(String.format("%s", mValues.get(position).getStartLetter()));
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(String.format("%,.1f", mValues.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLetterView;
        public final TextView mIdView;
        public final TextView mContentView;
        public BazaarItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLetterView = view.findViewById(R.id.item_letter);
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}