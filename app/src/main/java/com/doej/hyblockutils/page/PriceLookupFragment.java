package com.doej.hyblockutils.page;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.item.AuctionItem;
import com.doej.hyblockutils.main.MainActivity;
import com.doej.hyblockutils.util.ApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 *
 * @author doej1367
 */
public class PriceLookupFragment extends UpdatableFragment {

    ArrayList<AuctionItem> auctionItems = new ArrayList<>();
    String result = "";

    public PriceLookupFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price_lookup, container, false);
        TextView output = view.findViewById(R.id.priceLookupOutput);
        output.setMovementMethod(new ScrollingMovementMethod());
        output.setTextSize(getTextSize());
        output.setText(result);
        return view;
    }

    @Override
    public Status updateData(ProgressBar progressBar) {
        try {
            JSONObject o = new ApiRequest().getAuctionsPage(0);
            if (!o.getBoolean("success") && o.getString("cause").contains("Invalid API key"))
                return Status.INVALID_KEY;
            int pages = o.getInt("totalPages");

            int maxResultCount = 25; // TODO add a setting for this value?
            auctionItems.clear();
            if (MainActivity.isLimitPageRequests())
                pages = Math.min(pages, 10);
            progressBar.setMax(pages + maxResultCount);
            progressBar.setProgress(1);

            addPage(o);
            addPages(pages, progressBar);
            Collections.sort(auctionItems);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%s search result(s) for:\n    %s\n\n", auctionItems.size(), queryWords.toString()));
            if (MainActivity.isLimitPageRequests())
                sb.append("Info: limitedPageRequests are enabled\n\n");
            boolean noResults = true;
            for (int i = 0; i < Math.min(auctionItems.size(), maxResultCount); i++) {
                AuctionItem a = auctionItems.get(i);
                sb.append(String.format("%s\n  %s - %s%s\n", a.getItemName(), new ApiRequest().getName(a.getAuctioneer()), formatLong(a.getPrice()), (a.isBin() ? " (BIN)" : "")));
                noResults = false;
                progressBar.setProgress(pages + i);
            }
            if (noResults)
                sb.append("<no results found>\n");
            result = sb.toString();
            return Status.OK;
        } catch (JSONException | InterruptedException e) {
            return Status.ERROR;
        }

    }

    /**
     * Sends up to 4 parallel requests to get, parse and filter the data from auction house pages
     *
     * @param pages - the page numbers from 1 to <code>pages</code> to get
     * @param progressBar - the progress bar that gets updated while the pages are requested
     * @throws InterruptedException - thrown in case some of the threads are interrupted
     */
    private void addPages(int pages, ProgressBar progressBar) throws InterruptedException {
        Thread[] requestThreads = new Thread[pages - 1];
        int threadCount = Math.min(pages - 1, 4);
        for (int i = 1; i < pages; i++) {
            int finalI = i;
            requestThreads[i - 1] = new Thread() {
                @Override
                public void run() {
                    try {
                        addPage(new ApiRequest().getAuctionsPage(finalI));
                    } catch (JSONException ignored) {

                    }
                }
            };
            requestThreads[i - 1].start();
            progressBar.setProgress(i);
            if (i > threadCount)
                requestThreads[i - threadCount - 1].join();
        }
        for (int i = pages - threadCount; i < pages; i++)
            requestThreads[i - 1].join();
    }

    /**
     * filter the items in the JSON-data from one auction house page by <code>queryWords</code>
     * and add them to the <code>auctionItems</code>
     *
     * @param o - the JSON-data from an auction house page
     * @throws JSONException - thrown in case something is wrong with the auction house page data
     */
    private void addPage(JSONObject o) throws JSONException {
        JSONArray array = o.getJSONArray("auctions");
        for (int i = 0; i < array.length(); i++) {
            AuctionItem a = new AuctionItem(array.getJSONObject(i));
            if (a.contains(queryWords))
                auctionItems.add(a);
        }
    }

    private String formatLong(long d) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        return formatter.format(d);
    }

}