

package com.slava.theapp.data;


import android.content.Context;

import com.slava.theapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
  /*  private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
*/
    @Inject
    public AppDataManager(@ApplicationContext Context context
                         /*, DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper*/) {
        mContext = context;
       /* mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;*/
    }
}
