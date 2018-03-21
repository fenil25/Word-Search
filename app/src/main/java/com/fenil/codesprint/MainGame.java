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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.SimpleTimeZone;

public class MainGame extends AppCompatActivity {

    TextView tv, gameStatus;
    String[][] twod; String submit_word="";
    ArrayList<Integer> clicked = new ArrayList<>();
    //TrieNode root = null;
    //Trie trie=new Trie();
    HashMap<String, String[]> store;
    WordTrie trie=new WordTrie();
    int score=0, counter=1;
    ArrayList<String> submitted_words = new ArrayList<>();
    String parent="";

    public boolean isAdjacent(String chils) {
        String child;
        if(chils.length()==1)
            {child = twod[0][(int)chils.charAt(0)-48];}
        else
            {child = twod[(int)chils.charAt(0)-48][(int)chils.charAt(1)-48];}
        Log.e("parent",parent+"1");
        Log.e("child",child);
        if(parent.equals("")) {
            Log.e("parent",parent+"2");
            parent=child;
            return true;
        }
        else {
            String x[] = store.get(parent);
            ArrayList<String> cx = new ArrayList<String>(Arrays.asList(x));
            Log.e("adj",Arrays.toString(x));
            if (cx.contains(child)) {
                parent = child;
                return true;
            }
        }
        return false;
    }

    public void select(View view) {
        tv = (TextView)findViewById(view.getId());
        if(isAdjacent(view.getTag()+"") && !clicked.contains(tv.getText())) {
            Log.e("msg", "adjacent");
            view.setBackgroundColor(0xff669900);
            clicked.add(view.getId());
//        String s = "text"+i+j;
//        int x = getResources().getIdentifier(s,"id",getPackageName());
            submit_word += tv.getText();
            if (trie.isPrefix(submit_word)) {
                gameStatus.setText("Word is possible!");
            } else {
                gameStatus.setText("Word is not possible!");
                reset(view);
            }
            Log.e("len", submit_word.length() + "");
            if (submit_word.length() >= 3) {
                Log.e("as", trie.contais(submit_word) + "");
                if (trie.contais(submit_word)) {
                    if (!submitted_words.contains(submit_word)) {
                        submitted_words.add(submit_word);
                        score += submit_word.length() * counter;
                        counter++;
                        gameStatus.setText(score + "");
                        reset(view);
                    } else {
                        gameStatus.setText("Ullu banaega kya?");
                        reset(view);
                    }
                }
            }
        }
        else {
            gameStatus.setText("Kya be?");
        }
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
        submit_word = "";
        parent="";
    }
//
//    public ArrayList<String> possible(int i,int j) {
//        String parent=twod[i][j],word=parent;
//        ArrayList<String> adjacent=adjacentLetters(i,j);
//        for(int k=0;k<adjacent.size();k++) {
//            if(getChild(parent, adjacent.get(k))) {
//                word +=adjacent.get(k);
//                parent = adjacent.get(k);
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Random random=new Random();
        gameStatus = (TextView) findViewById(R.id.gameStatus);
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
                continue;
            }
            else {
                d+=65;
                c = (char)d+"";
            }
            alphabets.add(c);
        }
        Collections.shuffle(alphabets);

        int k=0;
        twod= new String[5][5];
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
                //ArrayList<String> possibleWords=possible(i,j);
            }
        }


        AssetManager assetManager = getAssets();
        try {
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(assetManager.open("words.txt")));
            //root = new TrieNode();
            String line = null;
            while((line = br.readLine()) != null) {
                String word = line.trim();
                if (word.length() >= 3)
                    trie.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainWorkingClass mwc = new MainWorkingClass(twod);
        store = mwc.start();
    }
}
