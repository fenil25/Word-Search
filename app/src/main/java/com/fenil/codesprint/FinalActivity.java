package com.fenil.codesprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i = getIntent();
        int score = i.getIntExtra("score", 0);
        sc = (TextView) findViewById(R.id.textView2);
        sc.setText(score+"");
    }
}
