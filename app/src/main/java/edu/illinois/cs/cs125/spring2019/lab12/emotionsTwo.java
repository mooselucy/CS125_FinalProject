package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class emotionsTwo extends AppCompatActivity {
    public static SharedPreferences ColorTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions_two);
        //Setting emotion colors
        ColorTwo = getSharedPreferences("setColorTwoID", MODE_PRIVATE);
        //submit goes back to main page
        final RadioButton blueButton = (RadioButton) findViewById(R.id.Great);
        final RadioButton greenButton = (RadioButton) findViewById(R.id.Normal);
        final RadioButton purpleButton = (RadioButton) findViewById(R.id.Sad);
        final Button submit = (Button) findViewById(R.id.submitColor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blueButton.isChecked()) {
                    String colour = ColorTwo.getString("blues", null);
                    SharedPreferences.Editor preferencesEditor = ColorTwo.edit();
                    preferencesEditor.putString("colorKey1","blues"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (purpleButton.isChecked()) {
                    String colour = ColorTwo.getString("purples", null);
                    SharedPreferences.Editor preferencesEditor = ColorTwo.edit();
                    preferencesEditor.putString("colorKey1","purples"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (greenButton.isChecked()) {
                    String colour = ColorTwo.getString("greens", null);
                    SharedPreferences.Editor preferencesEditor = ColorTwo.edit();
                    preferencesEditor.putString("colorKey1","greens"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }

            }
        });
    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
