package edu.illinois.cs.cs125.spring2019.lab12;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;

public class TaskThree extends AppCompatActivity {
    private EditText taskThree;
    private Button saveButt;
    private Button voiceText;
    SpeechRecognizer speechRecognize;
    Intent speechRecognizeIntent;
    public static SharedPreferences taskNameThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_three);
        checkPermission();
        speechRecognize = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizeIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizeIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizeIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognize.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    taskThree.setText(matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        saveButt = (Button) findViewById(R.id.saveButton);
        taskThree = (EditText) findViewById(R.id.editTask);
        taskNameThree = getSharedPreferences("taskThreeID", MODE_PRIVATE);
        saveButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskThree.getText().length() > 0) {
                    makeTag(taskThree.getText().toString());
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(taskThree.getWindowToken(),0 );
                }
                goBack();
            }
        });
        String two = taskNameThree.getString("keyThree", "Task Three");
        taskThree.setText(two);
        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        voiceText = findViewById(R.id.recordVoice);
        findViewById(R.id.recordVoice).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        speechRecognize.stopListening();
                        taskThree.setHint("Enter Task.");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        taskThree.setText("");
                        taskThree.setHint("Listening...");
                        speechRecognize.startListening(speechRecognizeIntent);
                        break;
                }
                return false;
            }
        });
    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED)) {
                Intent intn = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intn);
                finish();
            }
        }
    }
    private void makeTag(String tag){
        String or = taskNameThree.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = taskNameThree.edit();
        preferencesEditor.putString("keyThree",tag); //change this line to this
        preferencesEditor.commit();

    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
