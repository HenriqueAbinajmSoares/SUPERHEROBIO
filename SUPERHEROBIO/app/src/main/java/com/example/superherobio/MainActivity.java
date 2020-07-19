package com.example.superherobio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String nameSuperhero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickSearch(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        EditText edtxtSearch = (EditText) findViewById(R.id.edtxtSearch);

        nameSuperhero = edtxtSearch.getText().toString();

        Intent intent = new Intent(this, ResultSearch.class);

        Bundle bundleHero = new Bundle();

        bundleHero.putString("key_nmHero", String.valueOf(nameSuperhero));

        intent.putExtras(bundleHero);

        startActivity(intent);
    }
}
