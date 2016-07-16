package com.example.deepanjali.gittest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    InputStream stream;
    public List<StackOverflowXmlParser.Item>countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        final TextView mTextView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://www.thehindu.com/?service=rss";
        String url = "http://feeds.bbci.co.uk/news/rss.xml";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));
                        stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));


//                        ProgressDialog progressDialog = null;
//                        // ...
//                        progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", true);
//                        new Thread() {
//                            public void run() {
//                                try{
//                                    // Grab your data
//                                } catch (Exception e) {
//                                }
//
//                                // When grabbing data is finish: Dismiss your Dialog
//                                progressDialog.dismiss();
//                            }+
//
//                        }.start();
//


                        StackOverflowXmlParser parser = new StackOverflowXmlParser();
                        try {
//                            countries = new ArrayList<StackOverflowXmlParser.Item>(parser.parse(stream));
//                            ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
//                                    "Loading. Please wait...", true);
                            countries.addAll(parser.parse(stream));
//                            countries.add(new StackOverflowXmlParser.Item("Html","The Powerful Hypertext markup language","www.google.com"));
//                            countries.add(new StackOverflowXmlParser.Item("CSS","Cascading style sheet","www.yahoo.com"));


                            Log.d(String.valueOf(countries.size()),"what the");

                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                            Log.d(e.getLocalizedMessage(),"what the hell");
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(e.getLocalizedMessage(), "what ");

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
//        countries = new List<StackOverflowXmlParser.Item>();
        countries = new ArrayList<>();


//
//        countries.add(new StackOverflowXmlParser.Entry("Html","The Powerful Hypertext markup language","www.google.com"));
//        countries.add(new CustomList("CSS","Cascading style sheet"));
//        countries.add(new CustomList("Javascript","Code with Javascript"));
//        countries.add(new CustomList("Java","Code with Java ,Independent Platform"));


        RecyclerView.Adapter adapter = new DataAdapter(countries);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    final int position = rv.getChildAdapterPosition(child);
//                    Toast.makeText(getApplicationContext(), countries.get(position).link, Toast.LENGTH_SHORT).show();
//                    countries.get(position).link;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
