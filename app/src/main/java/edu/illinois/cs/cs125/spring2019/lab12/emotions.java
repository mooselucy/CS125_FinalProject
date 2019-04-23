package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class emotions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);
        //Setting emotion colors
        //submit goes back to main page
        final RadioButton Blue = (RadioButton) findViewById(R.id.Great);
        final RadioButton Green = (RadioButton) findViewById(R.id.Normal);
        final RadioButton Purple = (RadioButton) findViewById(R.id.Sad);
        final Button submit = (Button) findViewById(R.id.submitColor);
        Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBlue();
            }
        });
        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGreen();
            }
        });
        Purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPurple();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });


    }
    public void setBlue() {
        // set the button "editTask in activity_main" as blue
    }
    public void setGreen() {
        // set the button "editTask in activity_main" as green
    }
    public void setPurple() {
        // set the button "editTask in activity_main" as purple
    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
