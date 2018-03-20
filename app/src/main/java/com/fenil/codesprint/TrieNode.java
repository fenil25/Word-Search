package com.fenil.codesprint;
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
        {
            if(!temp.containsKey(s.charAt(i)))
            {
                temp.put(s.charAt(i), new TrieNode());
            }
            if(i==s.length()-1)
            {
                temp.get(s.charAt(i)).isWord = true;
            }
            temp = temp.get(s.charAt(i)).children;
        }
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
