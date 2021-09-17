package com.doej.hyblockutils.item;

import com.doej.hyblockutils.nedit.NBTReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an item from the Hypixel SkyBlock auction house
 *
 * @author doej1367
 */
public class AuctionItem implements Comparable<AuctionItem> {
    private String item_name = "";
    private String item_lore = "";
    private String auctioneer = "";
    private long secondsLeft = 0;
    private long starting_bid = 0;
    private long highest_bid_amount = 0;

    private long price = 0;
    private boolean bin = false;
    private int count = 1;

    public AuctionItem(JSONObject jsonObject) {
        try {
            item_name = jsonObject.getString("item_name");
            item_lore = jsonObject.getString("item_lore").replaceAll("§.", "").replaceAll("\n", " ").replaceAll(" {2}", " ");
            auctioneer = jsonObject.getString("auctioneer");
            secondsLeft = (jsonObject.getLong("end") - System.currentTimeMillis()) / 1000;
            starting_bid = jsonObject.getLong("starting_bid");
            highest_bid_amount = jsonObject.getLong("highest_bid_amount");
            price = Math.max(starting_bid, highest_bid_amount);
            bin = jsonObject.getBoolean("bin");
            try {
                count = new JSONObject(NBTReader.readBase64(jsonObject.getString("item_bytes"))).getInt("Count");
            } catch (IOException e) {
                //e.printStackTrace();
            }
        } catch (JSONException e) {
            //e.printStackTrace();
        }
    }

    public boolean contains(ArrayList<String> queryWords) {
        int found = 0;
        for (String s : queryWords)
            if (item_name.toLowerCase().contains(s) || item_lore.toLowerCase().contains(s))
                found++;
        return found == queryWords.size();
    }

    public String getItemName() {
        return item_name;
    }

    @SuppressWarnings("unused")
    public String getItemLore() {
        return item_lore;
    }

    public String getAuctioneer() {
        return auctioneer;
    }

    @SuppressWarnings("unused")
    public long getSecondsLeft() {
        return secondsLeft;
    }

    @SuppressWarnings("unused")
    public long getStartingBid() {
        return starting_bid;
    }

    @SuppressWarnings("unused")
    public long getHighestBidAmount() {
        return highest_bid_amount;
    }

    public boolean isBin() {
        return bin;
    }

    @SuppressWarnings("unused")
    public int getCount() {
        return count;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public int compareTo(AuctionItem a) {
        return Long.compare(getPrice(), a.getPrice());
    }
}
