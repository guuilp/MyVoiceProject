package com.example.guilherme.myvoiceproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener{

    public ListView mList;
    public Button speakButton;
    public WebView myWebView;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakButton = (Button) findViewById(R.id.button);
        speakButton.setOnClickListener(this);
        voiceinputbuttons();

        myWebView = (WebView) findViewById(R.id.webView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        startVoiceRecognitionActivity();
    }

    public void voiceinputbuttons() {
        speakButton = (Button) findViewById(R.id.button);
        mList = (ListView) findViewById(R.id.listView);
    }

    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Trabalho do Thales");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this, "Pesquisando por " + matches.get(1), Toast.LENGTH_LONG).show();
            String url = "https://www.google.com.br/search?q=" + matches.get(1);
            myWebView.loadUrl(url);
//            mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
//            if (matches.contains("information")) {
//                informationMenu();
//            }
        }
    }

    public void informationMenu() {
        startActivity(new Intent("android.intent.action.INFOSCREEN"));
    }

}
