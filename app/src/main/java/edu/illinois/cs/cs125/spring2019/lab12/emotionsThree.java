package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class emotionsThree extends AppCompatActivity {
    public static SharedPreferences ColorThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions_three);
        //Setting emotion colors
        ColorThree = getSharedPreferences("setColorThreeID", MODE_PRIVATE);
        //submit goes back to main page
        final RadioButton blueButton = (RadioButton) findViewById(R.id.Great);
        final RadioButton greenButton = (RadioButton) findViewById(R.id.Normal);
        final RadioButton purpleButton = (RadioButton) findViewById(R.id.Sad);
        final Button submit = (Button) findViewById(R.id.submitColor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blueButton.isChecked()) {
                    String colour = ColorThree.getString("blues", null);
                    SharedPreferences.Editor preferencesEditor = ColorThree.edit();
                    preferencesEditor.putString("colorKey2","blues"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (purpleButton.isChecked()) {
                    String colour = ColorThree.getString("purples", null);
                    SharedPreferences.Editor preferencesEditor = ColorThree.edit();
                    preferencesEditor.putString("colorKey2","purples"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (greenButton.isChecked()) {
                    String colour = ColorThree.getString("greens", null);
                    SharedPreferences.Editor preferencesEditor = ColorThree.edit();
                    preferencesEditor.putString("colorKey2","greens"); //change this line to this
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
