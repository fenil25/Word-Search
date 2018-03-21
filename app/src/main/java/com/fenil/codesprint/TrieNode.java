package com.fenil.codesprint;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        HashMap<Character, TrieNode> temp = children;
        for(int i = 0;i<s.length();i++)

    public boolean isWordPossible(String s){
        TrieNode temp = this;
        for(int i=0;i<s.length();i++)
        {
            if(temp.children.containsKey(s.charAt(i))){
                temp = temp.children.get(s.charAt(i));
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean isWord(String s) {
        TrieNode temp = searchNode(s);
        if (temp == null){
            return false;
        }
        else
            return temp.isWord;
    }

    private TrieNode searchNode(String s){
        TrieNode temp = this;
        for(int i=0;i<s.length();i++){
            if(!temp.children.containsKey(s.charAt(i))){
                return null;
            }
            temp = temp.children.get(s.charAt(i));
        }
        return temp;
    }
}
