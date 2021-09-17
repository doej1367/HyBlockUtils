package com.doej.hyblockutils.util;

import com.doej.hyblockutils.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author doej1367
 */
public class ApiRequest {
    private final String apiKey;

    public ApiRequest() {
        apiKey = MainActivity.getApiKey();
    }

    public String getUUID(String name) {
        try {
            return getJsonObjectFromApi("https://api.mojang.com/users/profiles/minecraft/" + name).getString("id");
        } catch (JSONException e) {
            return "<error>";
        }
    }

    public String getName(String uuid) {
        try {
            JSONArray tmp = getJsonArrayFromApi("https://api.mojang.com/user/profiles/" + uuid + "/names");
            return tmp.getJSONObject(tmp.length() - 1).getString("name");
        } catch (JSONException e) {
            return "<error>";
        }
    }

    public JSONObject getProfile(String name) {
        return getJsonObjectFromApi("https://api.hypixel.net/skyblock/profiles?key=" + apiKey + "&uuid=" + getUUID(name));
    }

    public JSONObject getAuctionsPage(int page) {
        return getJsonObjectFromApi("https://api.hypixel.net/skyblock/auctions?key=" + apiKey + "&page=" + page);
    }

    public JSONObject getBazaar() {
        return getJsonObjectFromApi("https://api.hypixel.net/skyblock/bazaar?key=" + apiKey);
    }

    public JSONObject getJsonObjectFromApi(String url) {
        try {
            return new JSONObject(getJsonFromApi(url));
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONArray getJsonArrayFromApi(String url) {
        try {
            return new JSONArray(getJsonFromApi(url));
        } catch (JSONException e) {
            return null;
        }
    }

    public String getJsonFromApi(String url) {
        int responseCode = 0;
        try {
            HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            urlConnection.setSSLSocketFactory(new TLSSocketFactory());
            responseCode = urlConnection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = br.read()) != -1)
                sb.append((char) c);
            br.close();
            return sb.toString();
        } catch (IOException e) {
            if (url.contains("hypixel") && responseCode == 403)
                return "{\"success\":false,\"cause\":\"Invalid API key\"}";
            if (url.contains("hypixel") && responseCode == 429)
                return "{\"success\":false,\"cause\":\"Request limit\"}";
            return "{\"success\":false,\"cause\":\"Error " + responseCode + "\"}";
        } catch (NoSuchAlgorithmException ignore) {

        } catch (KeyManagementException ignore) {

        }
        return "";
    }

}
