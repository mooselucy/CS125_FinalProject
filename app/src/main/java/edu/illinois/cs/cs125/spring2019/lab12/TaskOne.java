package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TaskOne extends AppCompatActivity {
    private EditText taskOne;
    private Button saveButt;
    public static SharedPreferences taskNameOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_one);
        saveButt = (Button) findViewById(R.id.saveButton);
        taskOne = (EditText) findViewById(R.id.editTask);
        taskNameOne = getSharedPreferences("taskOneID", MODE_PRIVATE);

        //taskOne.setText(taskNameOne.getString("tag", taskOne.getText().toString()));
        saveButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskOne.getText().length() > 0) {
                    makeTag(taskOne.getText().toString());
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(taskOne.getWindowToken(),0 );
                }
                goBack();
            }
        });
        String one = taskNameOne.getString("keyOne", "Task One");
        taskOne.setText(one);
        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }
    private void makeTag(String tag){
        String or = taskNameOne.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = taskNameOne.edit();
        preferencesEditor.putString("keyOne",tag); //change this line to this
        preferencesEditor.commit();

    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
