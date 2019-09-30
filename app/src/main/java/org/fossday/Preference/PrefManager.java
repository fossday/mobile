package org.fossday.Preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor sharedPreferencesEditor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String TAG = "PrefManager";
    private static final String PREFERENCE_NAME = "FOSSDay";

    private static final String KEY_FIRST_TIME_LAUCH = "firstTime";
    private static final String KEY_GIFT_OPENED = "giftOpened";

    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        sharedPreferencesEditor.putBoolean(KEY_FIRST_TIME_LAUCH, isFirstTime);
        sharedPreferencesEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(KEY_FIRST_TIME_LAUCH, true);
    }

    public void setGiftOpened(boolean isGiftOpened) {
        sharedPreferencesEditor.putBoolean(KEY_GIFT_OPENED, isGiftOpened);
        sharedPreferencesEditor.commit();
    }

    public boolean isGiftOpened() {
        return sharedPreferences.getBoolean(KEY_GIFT_OPENED, false);
    }
}
