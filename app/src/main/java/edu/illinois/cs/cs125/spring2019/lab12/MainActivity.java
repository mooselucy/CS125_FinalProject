package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Emotion/Task Manager";
    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    /** TODAYS DATE */
    private final Calendar c = Calendar.getInstance();
    private final int year = c.get(Calendar.YEAR);
    private int month = c.get(Calendar.MONTH);
    private int day = c.get(Calendar.DAY_OF_MONTH);
    private Button taskOne;
    private SharedPreferences taskOneName;
    private Button taskTwo;
    private SharedPreferences taskTwoName;
    private Button taskThree;
    private SharedPreferences taskThreeName;
    private SharedPreferences Color;
    private SharedPreferences ColorTwo;
    private SharedPreferences ColorThree;


    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.APIbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAPICall("192.17.96.8");
            }
        });


        //The task_button pulls up new page
        final android.support.design.widget.FloatingActionButton plus =
                (android.support.design.widget.FloatingActionButton) findViewById(R.id.addTask);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View add) {
                openSetTask();
            }
        });
        //When button is clicked, new screen pops up
        final Button checkClickOne = (Button) findViewById(R.id.checkBox);
        checkClickOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmotionsOne();
            }
        });
        final Button checkClickTwo = (Button) findViewById(R.id.checkBoxTwo);
        checkClickTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmotionsTwo();
            }
        });
        final Button checkClickThree = (Button) findViewById(R.id.checkBoxThree);
        checkClickThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmotionsThree();
            }
        });

        //Edit the TASK ONE
        taskOne = (Button) findViewById(R.id.editTaskOne);
        taskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditTaskOne();
            }
        });
        taskOneName = getSharedPreferences("taskOneID", Context.MODE_PRIVATE);
        String one = taskOneName.getString("keyOne", "Task One");
        taskOne.setText(one);

        //EDIT THE TASK TWO
        taskTwo = (Button) findViewById(R.id.editTaskTwo);
        taskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditTaskTwo();
            }
        });
        taskTwoName = getSharedPreferences("taskTwoID", Context.MODE_PRIVATE);
        String two = taskTwoName.getString("keyTwo", "Task Two");
        taskTwo.setText(two);

        //Edit THE TASK THREE
        taskThree = (Button) findViewById(R.id.editTaskThree);
        taskThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditTaskThree();
            }
        });
        taskThreeName = getSharedPreferences("taskThreeID", Context.MODE_PRIVATE);
        String three = taskThreeName.getString("keyThree", "Task Three");
        taskThree.setText(three);

        //EmITONS FIRST
        Color = getSharedPreferences("setColorID", Context.MODE_PRIVATE);
        String colour = Color.getString("colorKey", null);
        if (colour.equals("blues")) {
            taskOne.setBackgroundColor(0xff33b5e5);
        }
        if (colour.equals("purples")) {
            taskOne.setBackgroundColor(0xffaa66cc);
        }
        if (colour.equals("greens")) {
            taskOne.setBackgroundColor(0xff99cc00);
        }
        //EmOTIONS TWO
        ColorTwo = getSharedPreferences("setColorTwoID", Context.MODE_PRIVATE);
        String colourTwo = ColorTwo.getString("colorKey1", null);
        if (colourTwo != null) {
            if (colourTwo.equals("blues")) {
                taskTwo.setBackgroundColor(0xff33b5e5);
            }
            if (colourTwo.equals("purples")) {
                taskTwo.setBackgroundColor(0xffaa66cc);
            }
            if (colourTwo.equals("greens")) {
                taskTwo.setBackgroundColor(0xff99cc00);
            }
        }
        //Emotions Three
        ColorThree = getSharedPreferences("setColorThreeID", Context.MODE_PRIVATE);
        String colourThree = ColorThree.getString("colorKey2", null);
        if (colourThree != null) {
            if (colourThree.equals("blues")) {
                taskThree.setBackgroundColor(0xff33b5e5);
            }
            if (colourThree.equals("purples")) {
                taskThree.setBackgroundColor(0xffaa66cc);
            }
            if (colourThree.equals("greens")) {
                taskThree.setBackgroundColor(0xff99cc00);
            }
        }



        //Yesterday and tomro goes back and forth
        final ImageButton yest = (ImageButton) findViewById(R.id.toYesterday);
        final ImageButton tomr = (ImageButton) findViewById(R.id.toTomorrow);
        yest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesterday();
            }
        });
        tomr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomorrow();
            }
        });
        //Set the TODAY date
        TextView dateView = (TextView) findViewById(R.id.date);
        dateView.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

    }
    public void yesterday() {
        TextView dateView = (TextView) findViewById(R.id.date);
        day = day - 1;
        if (day > 30) {
            month = month + 1;
            day = 1;
        }
        dateView.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
    }
    public void tomorrow() {
        TextView dateView = (TextView) findViewById(R.id.date);
        day = day + 1;
        if (day > 30) {
            month = month + 1;
            day = 1;
        }
        dateView.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
    }
    public void openEmotionsOne() {
        Intent intent = new Intent(this, emotions.class);
        startActivity(intent);
    }
    public void openEmotionsTwo() {
        Intent intent = new Intent(this, emotionsTwo.class);
        startActivity(intent);
    }
    public void openEmotionsThree() {
        Intent intent = new Intent(this, emotionsThree.class);
        startActivity(intent);
    }
    public void openSetTask() {
        Intent intent = new Intent(this, setUpTask.class);
        startActivity(intent);
    }
    public void openEditTaskOne() {
        Intent intent = new Intent(this, TaskOne.class);
        startActivity(intent);
    }
    public void openEditTaskTwo() {
        Intent intent = new Intent(this, TaskTwo.class);
        startActivity(intent);
    }
    public void openEditTaskThree() {
        Intent intent = new Intent(this, TaskThree.class);
        startActivity(intent);
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    void startAPICall(final String ipAddress) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://ipinfo.io/" + ipAddress + "/json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
        } catch (JSONException ignored) { }
    }
}
