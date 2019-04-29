package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TaskThree extends AppCompatActivity {
    private EditText taskThree;
    private Button saveButt;
    public static SharedPreferences taskNameThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_three);
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
        String three = taskNameThree.getString("keyThree", "Task Three");
        taskThree.setText(three);
        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
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
