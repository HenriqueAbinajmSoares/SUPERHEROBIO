package com.example.superherobio;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String SUPERHEROES_URL = "https://superheroapi.com/api/access-token/character-id/biography";
    private static final String QUERY_PARAM = "";
    private static final String MAX_RESULTS = "maxResults";
    private static final String TIPO_IMPRESSAO = "printType";
        static String searchSuperHeroInfo(String queryString){
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String superheroJSONString = null;
            try {
                Uri builtURI = Uri.parse(SUPERHEROES_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, queryString)
                        .appendQueryParameter(MAX_RESULTS, "10")
                        .appendQueryParameter(TIPO_IMPRESSAO, "superhero")
                        .build();
                URL requestURL = new URL(builtURI.toString());

                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder builder = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    builder.append(linha);
                    builder.append("\n");
                }
                if (builder.length() == 0) {
                    return null;
                }
                superheroJSONString = builder.toString();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if (reader != null){
                    try {
                        reader.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
            Log.d(LOG_TAG, superheroJSONString);
            return superheroJSONString;
        }
}
