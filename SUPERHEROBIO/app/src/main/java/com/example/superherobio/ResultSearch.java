package com.example.superherobio;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultSearch extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView NameHero, AlterEgoHero, AliasesHero, PlaceHero, FirstApHero, PubHero, AlignHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        Intent intent2 = getIntent();
        Bundle bundleHero = intent2.getExtras();

        NameHero = ((TextView) findViewById(R.id.lblNameHero));
        AlterEgoHero = ((TextView) findViewById(R.id.lblAlterEgoHero));
        AliasesHero = ((TextView) findViewById(R.id.lblAliasesEgoHero));
        PlaceHero = ((TextView) findViewById(R.id.lblPlaceHero));
        FirstApHero = ((TextView) findViewById(R.id.lblFirstApHero));
        PubHero = ((TextView) findViewById(R.id.lblPubHero));
        AlignHero = ((TextView) findViewById(R.id.lblAlignHero));

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null,this);
        }
        String queryString = bundleHero.getString("key_idHero");

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if(networkInfo != null && networkInfo.isConnected()
                && queryString.length() !=0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);
                NameHero.setText("Carregando...");
                AlterEgoHero.setText("Carregando...");
                AliasesHero.setText("Carregando...");
                PlaceHero.setText("Carregando...");
                FirstApHero.setText("Carregando...");
                PubHero.setText("Carregando...");
                AlignHero.setText("Carregando...");
        } else {
            if (queryString.length() == 0 ) {
                NameHero.setText("Informe um ID válido");
                AlterEgoHero.setText(" ");
                AliasesHero.setText(" ");
                PlaceHero.setText(" ");
                FirstApHero.setText(" ");
                PubHero.setText(" ");
                AlignHero.setText(" ");
            } else {
                NameHero.setText("Verifique sua conexão");
                AlterEgoHero.setText(" ");
                AliasesHero.setText(" ");
                PlaceHero.setText(" ");
                FirstApHero.setText(" ");
                PubHero.setText(" ");
                AlignHero.setText(" ");
            }
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if(args != null){
            queryString = args.getString("queryString");
        }
        return new LoadHeroes(this,queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String name = null;
            String alterego = null;
            String aliases = null;
            String place = null;
            String firstap = null;
            String pub = null;
            String align = null;

            while (i < itemsArray.length() &&
                    (name == null && alterego == null && aliases == null && place == null && firstap == null && pub == null && align == null)) {
                JSONObject hero = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = hero.getJSONObject("volumeInfo");
                try {
                    name = volumeInfo.getString("full-name");
                    alterego = volumeInfo.getString("alter-egos");
                    aliases = volumeInfo.getString("aliases");
                    place = volumeInfo.getString("place-of-birth");
                    firstap = volumeInfo.getString("first-appearance");
                    pub = volumeInfo.getString("publisher");
                    align = volumeInfo.getString("alignment");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if (name != null && alterego != null && aliases != null && place != null && firstap != null && pub != null && align != null) {
                NameHero.setText(name);
                AlterEgoHero.setText(alterego);
                AliasesHero.setText(aliases);
                PlaceHero.setText(place);
                FirstApHero.setText(firstap);
                PubHero.setText(pub);
                AlignHero.setText(align);
            } else {
                NameHero.setText("Nenhum resultado foi encontrado");
                AlterEgoHero.setText(" ");
                AliasesHero.setText(" ");
                PlaceHero.setText(" ");
                FirstApHero.setText(" ");
                PubHero.setText(" ");
                AlignHero.setText(" ");
            }
        } catch (Exception e){
            NameHero.setText("Nenhum resultado foi encontrado");
            AlterEgoHero.setText(" ");
            AliasesHero.setText(" ");
            PlaceHero.setText(" ");
            FirstApHero.setText(" ");
            PubHero.setText(" ");
            AlignHero.setText(" ");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void onClickVoltar(View l){
        Intent intentReturn = new Intent(this, MainActivity.class);
        startActivity(intentReturn);
    }
}
