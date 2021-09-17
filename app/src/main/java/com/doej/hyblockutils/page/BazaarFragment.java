package com.doej.hyblockutils.page;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.item.BazaarItem;
import com.doej.hyblockutils.util.ApiRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 *
 * @author doej1367
 */
public class BazaarFragment extends UpdatableFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int columnCount = 1;

    private final List<BazaarItem> products = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BazaarFragment() {
    }

    @SuppressWarnings("unused")
    public static BazaarFragment newInstance(int columnCount) {
        BazaarFragment fragment = new BazaarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bazaar_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (columnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
            }
            recyclerView.setAdapter(new BazaarListViewAdapter(products));
        }
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Status updateData(ProgressBar progressBar) {
        try {
            progressBar.setMax(3);
            progressBar.setProgress(1);
            JSONObject o = new ApiRequest().getBazaar();
            if (!o.getBoolean("success") && o.getString("cause").contains("Invalid API key"))
                return Status.INVALID_KEY;
            JSONArray tmp_products = o.getJSONObject("products").toJSONArray(o.getJSONObject("products").names());
            progressBar.setProgress(2);
            products.clear();
            if (tmp_products == null)
                return Status.ERROR;
            for (int i = 0; i < tmp_products.length(); i++)
                products.add(new BazaarItem(tmp_products.getJSONObject(i)));
            Iterator<BazaarItem> iterator = products.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getPrice() == 0)
                    iterator.remove();
            }
            Collections.sort(products);
            progressBar.setProgress(3);
            return (products.size() > 0) ? Status.OK : Status.ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        }
    }
}