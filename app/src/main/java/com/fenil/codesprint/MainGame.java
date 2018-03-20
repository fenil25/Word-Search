package com.fenil.codesprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainGame extends AppCompatActivity {

    TextView tv;

    public void select(View view) {
        view.setBackgroundColor(0xFFFF33);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        List<String> alphabets = new ArrayList<>();
        for(char k='A';k<='Z';k++)
        {
            String c = k+"";
            if(k=='U')
                {continue;}
            else if(k=='Q')
                {c = "Qu";}
            alphabets.add(c);
        }
        Collections.shuffle(alphabets);

        int k=0;
        for(int i=0;i<=4;i++)
        {
            for(int j = 0;j<=4;j++)
            {
                String s = "text"+i+j;
                int x = getResources().getIdentifier(s,"id",getPackageName());
                tv = (TextView)findViewById(x);
                tv.setText(alphabets.get(k));
                tv.setGravity(17);
                if(alphabets.get(k).equals("Qu"))
                    {
                        tv.setPadding(15,10,0,10);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
                    }
                k++;
            }
        }

        TrieNode root;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\AndroidStudioProjects\\ghost_starter\\app\\src\\main\\assets"));
            root = new TrieNode();
            String line = null;
            while((line = br.readLine()) != null) {
                String word = line.trim();
                Log.i("test", word);
                if (word.length() >= 3)
                    root.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
