package com.fenil.codesprint;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    TextView tv;
    ArrayList<Integer> clicked = new ArrayList<>();


    public void select(View view) {
        view.setBackgroundColor(0xff669900);
        clicked.add(view.getId());
    }

    public void reset(View view) {
        for (int i=0; i<clicked.size(); i++) {
            int x = clicked.get(i);
            TextView current = (TextView) findViewById(x);
            current.setBackgroundColor(0xffffff);
        }
        for (int i=clicked.size()-1; i>=0; i--) {
            clicked.remove(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Random random=new Random();
        List<String> alphabets = new ArrayList<>();
        alphabets.add("A");
        alphabets.add("E");
        alphabets.add("I");
        alphabets.add("O");
        alphabets.add("U");
        for(char k=0;k<21;k++)
        {
            int d=random.nextInt(26);
            String c;
            if(d==16) {
                c="Qu";
            }
            else {
                d+=65;
                c = (char)d+"";
            }
            alphabets.add(c);
        }
        Collections.shuffle(alphabets);

        int k=0;
        String[][] twod= new String[5][5];
        for(int i=0;i<=4;i++)
        {
            for(int j = 0;j<=4;j++)
            {
                String s = "text"+i+j;
                int x = getResources().getIdentifier(s,"id",getPackageName());
                tv = (TextView)findViewById(x);
                tv.setText(alphabets.get(k));
                twod[i][j]=alphabets.get(k);
                if(alphabets.get(k).equals("Qu")) {
                    tv.setPadding(15,10,0,10);
                }
                k++;
            }
        }

        TrieNode root = null;
        AssetManager assetManager = getAssets();
        try {
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(assetManager.open("words.txt")));
            root = new TrieNode();
            String line = null;
            while((line = br.readLine()) != null) {
                String word = line.trim();
                if (word.length() >= 3)
                    root.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainWorkingClass mwc = new MainWorkingClass(twod, root);
        HashMap<String, String[]> store;
        store = mwc.start();
    }
}
