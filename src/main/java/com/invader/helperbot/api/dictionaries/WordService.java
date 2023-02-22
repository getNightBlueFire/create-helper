package com.invader.helperbot.api.dictionaries;

import org.springframework.stereotype.Component;
import com.invader.helperbot.model.Dictionary;
import com.invader.helperbot.model.Word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class WordService {

    public List<Word> getDictionariesWordList(List<Dictionary> dictionaryList) {
        Set<Word> allWordSet = new HashSet<>();
        for (Dictionary dictionary : dictionaryList) {
            allWordSet.addAll(dictionary.getWordList());
        }
        return new ArrayList<>(allWordSet);
    }
}
