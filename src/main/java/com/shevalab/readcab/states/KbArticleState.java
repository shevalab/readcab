package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

import java.util.Collections;

public class KbArticleState extends CabPackagesBaseState {
    public KbArticleState(String name) {
        super(name);
    }

    @Override
    public BaseState processChars(char[] ch, int start, int length) {
        CabPackagesData cabPackagesData = getCabPackagesData();
        String kbArticle = new String(ch, start, length);
        cabPackagesData.getCurrentUpdate().setkBArticles(Collections.singletonList(kbArticle));
        return this;
    }
}
