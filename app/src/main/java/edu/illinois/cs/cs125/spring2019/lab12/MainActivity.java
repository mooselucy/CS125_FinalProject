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
    private final int month = c.get(Calendar.MONTH);
    private final int day = c.get(Calendar.DAY_OF_MONTH);
    public Button taskOne;
    public SharedPreferences taskOneName;
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
        //When checkbox is clicked, new screen pops up
        final CheckBox checkClick = (CheckBox) findViewById(R.id.checkBox);
        checkClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmotions();
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
        //if taskone is changed; the title of task changes
        taskOneName = getSharedPreferences("taskOneID", Context.MODE_PRIVATE);
        String one = taskOneName.getString("keyOne", "Task One");
        taskOne.setText(one);


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
        dateView.setText(new StringBuilder().append(month + 1).append("-").append(day - 1).append("-").append(year).append(" "));
    }
    public void tomorrow() {
        TextView dateView = (TextView) findViewById(R.id.date);
        dateView.setText(new StringBuilder().append(month + 1).append("-").append(day + 1).append("-").append(year).append(" "));
    }
    public void openEmotions() {
        Intent intent = new Intent(this, emotions.class);
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
