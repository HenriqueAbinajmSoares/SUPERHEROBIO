package com.example.superherobio;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LoadHeroes extends AsyncTaskLoader<String> {
    private String mQueryString;
    LoadHeroes(Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.searchSuperHeroInfo(mQueryString);
    }
}
