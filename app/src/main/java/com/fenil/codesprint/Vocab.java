package com.fenil.codesprint;

/**
 * Created by USER on 3/21/2018.
 */
public interface Vocab {
    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contais(String word);
}