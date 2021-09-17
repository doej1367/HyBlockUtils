package com.doej.hyblockutils.main;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.doej.hyblockutils.R;
import com.doej.hyblockutils.page.CalenderView;
import com.doej.hyblockutils.page.SettingsFragment;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * @author doej1367
 */
public class MainActivity extends AppCompatActivity {

    private MenuItem menuItem = null;
    private MainFragment mainFragment = null;
    private static Thread fetcherThread = new Thread();
    private static String name;
    private static String apiKey;
    private static boolean limitPageRequests;

    public static void setName(String name_preference) {
        name = name_preference;
    }

    public static String getName() {
        return name;
    }

    public static void setApiKey(String api_key_preference) {
        apiKey = api_key_preference;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setLimitRequests(boolean limit_page_requests) {
        limitPageRequests = limit_page_requests;
    }

    public static boolean isLimitPageRequests() {
        return limitPageRequests;
    }

    /**
     * stops the old thread and starts the new one
     *
     * @param fetcherThread - incorruptible thread that fetches information from a web connection
     */
    public static void setFetcherThread(Thread fetcherThread) {
        fetcherThread.interrupt();
        MainActivity.fetcherThread = fetcherThread;
        fetcherThread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            mainFragment = new MainFragment(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commitNow();
            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    if (menuItem.getTitle().toString().contains("Back"))
                        handleBackOperation();
                }
            };
            getOnBackPressedDispatcher().addCallback(this, callback);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.settings_icon);
        if (menuItem.getTitle().toString().isEmpty())
            menuItem.setTitle("Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().isEmpty())
            item.setTitle("Settings");
        if (item.getItemId() == R.id.settings_icon) {
            if (item.getTitle().toString().contains("Settings")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SettingsFragment())
                        .commitNow();
                showBackArrow();
            } else
                handleBackOperation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showBackArrow() {
        menuItem.setTitle("Back");
        menuItem.setIcon(R.drawable.ic_baseline_arrow_back_24);
    }

    private void handleBackOperation() {
        fetcherThread.interrupt();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow();
        menuItem.setTitle("Settings");
        menuItem.setIcon(R.drawable.ic_baseline_settings_24);
        // from https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-using-java
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null)
            view = new View(this);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     *
     * @return - text size for normal text fields
     */
    public int getTextSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int delta_x = size.x / 40;
        int delta_y = size.y / 27;
        return Math.abs(delta_x - 30) <= Math.abs(delta_y - 30) ? delta_x : delta_y;
    }

    /**
     *
     * @return - text size for text that is drawn on a canvas (e.g. {@link CalenderView})
     */
    public int getCanvasTextSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return (int) (Math.min(size.x / 29, size.y / 51) * 1.5);
    }

}