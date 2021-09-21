package com.innovertech.puzzlehero.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final String SHARED_PREFERENCE_NAME = "session";
    private final String SUB_ID = "sub_id";
    private static SessionManager sessionManagerInstance;

    public static SessionManager getInstance(Context context) {
        if (sessionManagerInstance == null) {
            sessionManagerInstance = new SessionManager(context);
        }
        return sessionManagerInstance;
    }


    public SessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }


    public void saveSubId(String subId) {
        editor.putString(SUB_ID, subId).commit();
    }

    public String getSubId() {
        String subId = sharedPreferences.getString(SUB_ID, "");
        return subId;
    }


    public void removeSession() {
        editor.putString(SUB_ID, "").commit();
    }

}
