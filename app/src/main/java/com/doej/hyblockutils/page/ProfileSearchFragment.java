package com.doej.hyblockutils.page;

import android.widget.ProgressBar;

import com.doej.hyblockutils.util.ApiRequest;

import org.json.JSONObject;

/**
 *
 * @author doej1367
 */
public class ProfileSearchFragment extends ProfileFragment {

    @Override
    public Status updateData(ProgressBar progressBar) {
        try {
            progressBar.setMax(3);
            progressBar.setProgress(1);
            uuid = new ApiRequest().getUUID(queryWords.get(0));
            progressBar.setProgress(2);
            JSONObject o = new ApiRequest().getProfile(queryWords.get(0));
            if (!o.getBoolean("success") && o.getString("cause").contains("Invalid API key"))
                return Status.INVALID_KEY;
            profiles = o.getJSONArray("profiles");
            progressBar.setProgress(3);
            return (profiles.length() > 0) ? Status.OK : Status.ERROR;
        } catch (Exception e) {
            return Status.ERROR;
        }
    }
}
