package Resourses;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by jcapasix on 21/06/18.
 */

public class SessionPrefs {


    public static final String BANNER_SESSION = "BANNER_SESSION";
    public static final String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";


    private final SharedPreferences mPrefs;
    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }
    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(BANNER_SESSION, Context.MODE_PRIVATE);
        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AFFILAITE_TOKEN, null));
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }


    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.apply();
    }
}