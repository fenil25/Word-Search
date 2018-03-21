package com.fenil.codesprint;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by USER on 3/21/2018.
 */

public class WordTrie implements Vocab {
    private List<String> words = new ArrayList<String>();
    TrieNode root=null;

    /**
     * Constructor that adds all the words and then sorts the underlying list
     */
//    public WordTrie(Collection<String> words) {
//        this.words.addAll(words);
//        Collections.sort(this.words);
//    }

    public boolean add(String word) {
        int pos = Collections.binarySearch(words, word);
        // pos > 0 means the word is already in the list. Insert only
        // if it's not there yet
        if (pos < 0) {
            words.add(-(pos+1), word);
            return true;
        }
        return false;
    }

    public void display() {
        for(int i=0;i<words.size();i++)
            Log.e("mes",words.get(i));
    }

    public boolean isPrefix(String prefix) {
        //int pos = Collections.binarySearch(words, prefix);
        //if (pos >= 0) {
            // The prefix is a word. Check the following word, because we are looking
            // for words that are longer than the prefix
        prefix=prefix.toLowerCase();
        for(int i=0;i<words.size();i++) {
            if(words.get(i).startsWith(prefix)) {
                Log.e("word",words.get(i));
                return true;
            }
            //if (pos +1 < words.size()) {
            //    String nextWord = words.get(pos+1);
            //    return nextWord.startsWith(prefix);
            //}
        }
        return false;

//        pos = -(pos+1);
        // The prefix is not a word. Check where it would be inserted and get the next word.
        // If it starts with prefix, return true.
  //      if (pos == words.size()) {
    //        return false;
    //    }
     //   String nextWord = words.get(pos);
      //  return nextWord.startsWith(prefix);
    }

    public boolean contais(String word) {
        int pos = Collections.binarySearch(words, word.toLowerCase());
        if(pos>0) {
            return true;
        }
        return false;
    }
}