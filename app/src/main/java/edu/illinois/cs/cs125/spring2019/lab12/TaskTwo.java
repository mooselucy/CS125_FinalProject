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

public class TaskTwo extends AppCompatActivity {
    private EditText taskTwo;
    private Button saveButt;
    public static SharedPreferences taskNameTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_two);
        saveButt = (Button) findViewById(R.id.saveButton);
        taskTwo = (EditText) findViewById(R.id.editTask);
        taskNameTwo = getSharedPreferences("taskTwoID", MODE_PRIVATE);

        saveButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskTwo.getText().length() > 0) {
                    makeTag(taskTwo.getText().toString());
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(taskTwo.getWindowToken(),0 );
                }
                goBack();
            }
        });
        String two = taskNameTwo.getString("keyTwo", "Task Two");
        taskTwo.setText(two);
        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }
    private void makeTag(String tag){
        String or = taskNameTwo.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = taskNameTwo.edit();
        preferencesEditor.putString("keyTwo",tag); //change this line to this
        preferencesEditor.commit();

    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
