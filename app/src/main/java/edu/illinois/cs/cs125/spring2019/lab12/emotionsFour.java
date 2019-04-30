package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class emotionsFour extends AppCompatActivity {
    public static SharedPreferences ColorFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions_four);
        //Setting emotion colors
        ColorFour = getSharedPreferences("setColorFourID", MODE_PRIVATE);
        //submit goes back to main page
        final RadioButton blueButton = (RadioButton) findViewById(R.id.Great);
        final RadioButton greenButton = (RadioButton) findViewById(R.id.Normal);
        final RadioButton purpleButton = (RadioButton) findViewById(R.id.Sad);
        final Button submit = (Button) findViewById(R.id.submitColor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blueButton.isChecked()) {
                    String colour = ColorFour.getString("blues", null);
                    SharedPreferences.Editor preferencesEditor = ColorFour.edit();
                    preferencesEditor.putString("colorKey3","blues"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (purpleButton.isChecked()) {
                    String colour = ColorFour.getString("purples", null);
                    SharedPreferences.Editor preferencesEditor = ColorFour.edit();
                    preferencesEditor.putString("colorKey3","purples"); //change this line to this
                    preferencesEditor.commit();
                    goBack();
                }
                if (greenButton.isChecked()) {
                    String colour = ColorFour.getString("greens", null);
                    SharedPreferences.Editor preferencesEditor = ColorFour.edit();
                    preferencesEditor.putString("colorKey3","greens"); //change this line to this
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
