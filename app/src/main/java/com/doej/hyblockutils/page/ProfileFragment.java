package com.doej.hyblockutils.page;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.item.InventoryItem;
import com.doej.hyblockutils.main.MainActivity;
import com.doej.hyblockutils.nedit.NBTReader;
import com.doej.hyblockutils.util.ApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author doej1367
 */
public class ProfileFragment extends UpdatableFragment {

    protected String uuid = null;
    protected JSONArray profiles = null;
    private JSONObject currentProfile = null;
    private final double[] catacombs_ladder = new double[]{0, 50, 125, 235, 395, 625, 955, 1425, 2095, 3045, 4385, 6275, 8940, 12700, 17960, 25340, 35640, 50040, 70040, 97640, 135640, 188140, 259640, 356640, 488640, 668640, 911640, 1239640, 1684640, 2284640, 3084640, 4149640, 5559640, 7459640, 9959640, 13259640, 17559640, 23159640, 30359640, 39559640, 51559640, 66559640, 85559640, 109559640, 139559640, 177559640, 225559640, 285559640, 360559640, 453559640, 569809640};
    private final double[] normal_ladder = new double[]{0, 50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925, 22425, 32425, 47425, 67425, 97425, 147425, 222425, 322425, 522425, 822425, 1222425, 1722425, 2322425, 3022425, 3822425, 4722425, 5722425, 6822425, 8022425, 9322425, 10722425, 12222425, 13822425, 15522425, 17322425, 19222425, 21222425, 23322425, 25522425, 27822425, 30222425, 32722425, 35322425, 38072425, 40972425, 44072425, 47472425, 51172425, 55172425, 59472425, 64072425, 68972425, 74172425, 79672425, 85472425, 91572425, 97972425, 104672425, 111672425};
    private final double[] zombie_slayer_ladder = new double[]{0, 5, 15, 200, 1000, 5000, 20000, 100000, 400000, 1000000};
    private final double[] spider_slayer_ladder = new double[]{0, 5, 25, 200, 1000, 5000, 20000, 100000, 400000, 1000000};
    private final double[] wolf_slayer_ladder = new double[]{0, 10, 30, 250, 1500, 5000, 20000, 100000, 400000, 1000000};
    private final double[] enderman_slayer_ladder = new double[]{0, 10, 30, 250, 1500, 5000, 20000, 100000, 400000, 1000000};

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String[] profile_names = new String[profiles.length()];
        int mostRecentProfile = 0;
        long mostRecentProfileAge = Long.MAX_VALUE;
        for (int i = 0; i < profile_names.length; i++) {
            try {
                long lastSaveAge = System.currentTimeMillis() - profiles.getJSONObject(i).getJSONObject("members")
                        .getJSONObject(uuid).getLong("last_save");
                if (lastSaveAge < mostRecentProfileAge) {
                    mostRecentProfileAge = lastSaveAge;
                    mostRecentProfile = i;
                }
                profile_names[i] = profiles.getJSONObject(i).getString("cute_name") +
                        " (" + round((lastSaveAge) / (1000.0 * 60 * 60 * 24)) + " d ago)";
            } catch (JSONException e) {
                e.printStackTrace();
                profile_names[i] = "";
            }
        }
        Spinner dropdown = view.findViewById(R.id.profileSelect);
        TextView output = view.findViewById(R.id.profileOutput);
        output.setMovementMethod(new ScrollingMovementMethod());
        output.setTextSize(getTextSize());
        dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, profile_names));
        dropdown.setSelection(mostRecentProfile);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    currentProfile = profiles.getJSONObject(i).getJSONObject("members").getJSONObject(uuid);
                    updateText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                    currentProfile = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        try {
            currentProfile = profiles.getJSONObject(mostRecentProfile).getJSONObject("members").getJSONObject(uuid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updateText(output);
        return view;
    }

    private void updateText(TextView output) {
        String errorMessage = "<Error: missing api values!>\n";
        StringBuilder sb = new StringBuilder();
        sb.append("Fairy Souls:\n  ");
        try {
            sb.append(String.format("%s\n", currentProfile.getInt("fairy_souls_collected")));
        } catch (JSONException e) {
            sb.append(errorMessage);
        }
        sb.append("Skill Average:\n  ");
        try {
            double skill_avg = 0;
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_farming", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_mining", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_combat", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_foraging", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_fishing", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_enchanting", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_alchemy", false));
            skill_avg += getLevel(normal_ladder, getDoubleByPath("experience_skill_taming", false));
            double cata_skill = getLevel(catacombs_ladder, getDoubleByPath("dungeons.dungeon_types.catacombs.experience", false));
            // skill_avg += cata_skill;
            boolean skillAvgExists = skill_avg > 0;
            skill_avg /= 8.0;
            sb.append(String.format("%s (excl. Catacombs %s)\n", skillAvgExists ? round(skill_avg) : errorMessage, round(cata_skill)));
        } catch (JSONException e) {
            sb.append(errorMessage);
        }
        HashSet<InventoryItem> items = new HashSet<>();
        sb.append("Accessory Score:\n  ");
        try {
            JSONArray inventory = new JSONObject(nbtCode(currentProfile.getJSONObject("inv_contents").getString("data"))).getJSONObject("").getJSONArray("i");
            for (int i = 0; i < inventory.length(); i++) {
                InventoryItem item = new InventoryItem(inventory.getJSONObject(i));
                if (!item.getItemType().isEmpty())
                    items.add(item);
            }
            JSONArray accessories = new JSONObject(nbtCode(currentProfile.getJSONObject("talisman_bag").getString("data"))).getJSONObject("").getJSONArray("i");
            for (int i = 0; i < accessories.length(); i++) {
                InventoryItem item = new InventoryItem(accessories.getJSONObject(i));
                if (!item.getItemType().isEmpty())
                    items.add(item);
            }
            int accessoryScore = 0;
            for (InventoryItem i : items)
                if (i.getItemType().equalsIgnoreCase("ACCESSORY"))
                    accessoryScore += i.getRarity(); // TODO double up on Hegemony Artifact?
            sb.append(String.format("%s\n", accessoryScore));
        } catch (JSONException | IOException e) {
            sb.append(errorMessage);
        }
        sb.append("\n");
        sb.append("Necron's Blade Variant:\n  ");
        try {
            JSONArray ender_chest = new JSONObject(nbtCode(currentProfile.getJSONObject("ender_chest_contents").getString("data"))).getJSONObject("").getJSONArray("i");
            for (int i = 0; i < ender_chest.length(); i++) {
                InventoryItem item = new InventoryItem(ender_chest.getJSONObject(i));
                if (!item.getItemType().isEmpty())
                    items.add(item);
            }
            int scrollCount = -1;
            InventoryItem tmp_item = null;
            for (InventoryItem i : items)
                // TODO a raw Necron Blade gets dismissed
                if (i.getItemId().equalsIgnoreCase("HYPERION") || i.getItemId().equalsIgnoreCase("VALKYRIE")
                        || i.getItemId().equalsIgnoreCase("ASTRAEA") || i.getItemId().equalsIgnoreCase("SCYLLA")) {
                    if (i.getScrollCount() > scrollCount) {
                        tmp_item = i;
                        scrollCount = i.getScrollCount();
                    }
                }
            if (scrollCount > -1)
                sb.append(String.format("%s (%s-Scroll)\n", tmp_item.getName(), scrollCount));
            else
                sb.append("NO\n");
        } catch (JSONException | IOException e) {
            sb.append(errorMessage);
        }
        sb.append("Armor:\n");
        try {
            JSONArray armor = new JSONObject(nbtCode(currentProfile.getJSONObject("inv_armor").getString("data"))).getJSONObject("").getJSONArray("i");
            for (int i = armor.length() - 1; i >= 0; i--) {
                InventoryItem item = new InventoryItem(armor.getJSONObject(i));
                if (!item.getItemType().isEmpty())
                    sb.append(String.format("  %s\n", item.getName()));
                else
                    sb.append("  <empty>\n");
            }
        } catch (JSONException | IOException e) {
            sb.append(String.format("  %s", errorMessage));
        }
        sb.append("\n");
        sb.append("Slayer Level:\n  ");
        try {
            double zombie = round(getLevel(zombie_slayer_ladder, getDoubleByPath("slayer_bosses.zombie.xp", false)));
            double spider = round(getLevel(spider_slayer_ladder, getDoubleByPath("slayer_bosses.spider.xp", false)));
            double wolf = round(getLevel(wolf_slayer_ladder, getDoubleByPath("slayer_bosses.wolf.xp", false)));
            double enderman = round(getLevel(enderman_slayer_ladder, getDoubleByPath("slayer_bosses.enderman.xp", false)));
            sb.append(String.format("%s  ·  %s  ·  %s  ·  %s\n", zombie, spider, wolf, enderman));
        } catch (JSONException e) {
            sb.append(errorMessage);
        }
        sb.append("Dungeons:\n  ");
        try {
            JSONObject completions = currentProfile.getJSONObject("dungeons").getJSONObject("dungeon_types").getJSONObject("catacombs").getJSONObject("tier_completions");
            for (int i = 0; i <= 7; i++)
                sb.append(String.format("%s%s", (i == 0 ? "" : ", "), (i > completions.length() - 1) ? 0 : completions.getInt("" + i)));
        } catch (JSONException e) {
            sb.append(errorMessage);
        }
        sb.append("\n");
        output.setText(sb.toString());
    }

    private double round(double skill_avg) {
        return Math.round(skill_avg * 10) / 10.0;
    }

    private double getDoubleByPath(String path, boolean throwError) throws JSONException {
        try {
            JSONObject o = currentProfile;
            String[] split = path.split("\\.");
            for (int i = 0; i < split.length - 1; i++)
                o = o.getJSONObject(split[i]);
            return o.getDouble(split[split.length - 1]);
        } catch (JSONException e) {
            if (throwError)
                throw e;
            else
                return 0;
        }
    }

    private double getLevel(double[] ladder, double experience) {
        int i = 0;
        for (; i < ladder.length - 1; i++)
            if (ladder[i + 1] > experience)
                break;
        double partial = (i + 1 <= ladder.length - 1) ? ((experience - ladder[i]) / (ladder[i + 1] - ladder[i])) : 0;
        return (double) i + partial;
    }

    public static String nbtCode(String inputString) throws IOException {
        return "" + NBTReader.readBase64(inputString);
    }

    @Override
    public Status updateData(ProgressBar progressBar) {
        try {
            progressBar.setMax(3);
            progressBar.setProgress(1);
            uuid = new ApiRequest().getUUID(MainActivity.getName());
            progressBar.setProgress(2);
            JSONObject o = new ApiRequest().getProfile(MainActivity.getName());
            if (!o.getBoolean("success") && o.getString("cause").contains("Invalid API key"))
                return Status.INVALID_KEY;
            profiles = o.getJSONArray("profiles");
            progressBar.setProgress(3);
            return (profiles.length() > 0) ? Status.OK : Status.ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        }
    }
}