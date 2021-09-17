package com.doej.hyblockutils.item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Represents an item from the Hypixel SkyBlock player inventory
 *
 * @author doej1367
 */
public class InventoryItem {
    private String name = "";
    private String itemId = "";
    private String itemType = "";
    private int rarity = 0;
    private int scrollCount = 0;

    public InventoryItem(JSONObject jsonObject) {
        try {
            JSONObject tag = jsonObject.getJSONObject("tag");
            this.itemId = tag.getJSONObject("ExtraAttributes").getString("id");
            JSONArray lore = tag.getJSONObject("display").getJSONArray("Lore");
            this.name = tag.getJSONObject("display").getString("Name").replaceAll("§.", "");
            String[] tmp = lore.getString(lore.length() - 1).split(" ");
            this.rarity = parseRarity(tmp[0].endsWith("r") ? tmp[1] : tmp[0]);
            this.itemType = tmp.length < 2 ? "" : tmp[tmp.length - 1].startsWith("§") ? tmp[tmp.length - 2] : tmp[tmp.length - 1];
            this.scrollCount = tag.getJSONObject("ExtraAttributes").getJSONArray("ability_scroll").length();
        } catch (JSONException e) {
            //e.printStackTrace();
        }
    }

    private int parseRarity(String rarity) {
        if (rarity.contains("UNCOMMON"))
            return 2;
        else if (rarity.contains("COMMON"))
            return 1;
        else if (rarity.contains("RARE"))
            return 3;
        else if (rarity.contains("EPIC"))
            return 4;
        else if (rarity.contains("LEGENDARY"))
            return 5;
        else if (rarity.contains("MYTHIC"))
            return 6;
        else if (rarity.contains("SUPREME"))
            return 7;
        else if (rarity.contains("SPECIAL"))
            return 8;
        else if (rarity.contains("VERY"))
            return 9;
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public int getRarity() {
        return rarity;
    }

    public int getScrollCount() {
        return scrollCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return getItemId().equals(((InventoryItem) o).getItemId());
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "itemId='" + itemId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", rarity=" + rarity +
                ", scrollCount=" + scrollCount +
                '}';
    }

}
