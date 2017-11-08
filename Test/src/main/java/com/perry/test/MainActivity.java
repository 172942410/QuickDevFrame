package com.perry.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.perry.http.parser.HttpUrlEntry;

public class MainActivity extends Activity {

    TextView textView;
    TextView titleTV;
    TextView urlTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.contentTextView);
        titleTV = (TextView)findViewById(R.id.item_tv_request_name);
        urlTV = (TextView)findViewById(R.id.item_tv_request_value);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        HttpUrlEntry httpUrlEntry = (HttpUrlEntry) intent.getSerializableExtra("HttpUrlEntry");
        titleTV.setText(httpUrlEntry.urlTitle);
        urlTV.setText(httpUrlEntry.urlAddress);
        textView.setText(content);
    }
}
