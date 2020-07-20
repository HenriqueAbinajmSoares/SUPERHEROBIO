package com.example.superherobio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String idSuperhero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void OnClickLoja(View view) {
            Uri webpage = Uri.parse("http://www.comix.com.br/");
            Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(intent);
        }

        public void OnClickIdList(View view) {
            Uri webpage = Uri.parse("https://superheroapi.com/ids.html");
            Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(intent);
        }

    public void OnClickSearch(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        EditText edtxtSearch = (EditText) findViewById(R.id.edtxtSearch);
        idSuperhero = edtxtSearch.getText().toString();

        Intent intent = new Intent(this, ResultSearch.class);
        Bundle bundleHero = new Bundle();
        bundleHero.putString("key_idHero", String.valueOf(idSuperhero));
        intent.putExtras(bundleHero);
        startActivity(intent);
    }
}
