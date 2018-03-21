package com.example.shahzaib.materialdesign_searchview_toolbar;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahzaib.materialdesign_searchview_toolbar.SearchToolbar.OnSearchToolbarQueryTextListner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSearchToolbarQueryTextListner  {

    SearchToolbar searchToolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Material Search View");

        searchToolbar = new SearchToolbar(this,this,findViewById(R.id.search_layout));
        textView = findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }
        return true;
    }

    /***** Get data from voice, because voice app will send data through intent & we want to receive */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SearchToolbar.VOICE_INTENT_REQUEST_CODE && data != null)
        {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this, "User Query: "+result.get(0), Toast.LENGTH_SHORT).show();
        }

    }


    /******* The following method will invoke when user Change or Submit text in SearchToolbar*/
    @Override
    public void onQueryTextSubmit(String query) {
        Toast.makeText(this, "User Query: "+query , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        textView.setText(editable);
    }


}
