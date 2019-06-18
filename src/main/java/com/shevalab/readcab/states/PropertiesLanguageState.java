package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

public class PropertiesLanguageState extends CabPackagesBaseState {
    public PropertiesLanguageState(String name) {
        super(name);
    }

    @Override
    public BaseState processChars(char[] ch, int start, int length) {
        CabPackagesData cabPackagesData = getCabPackagesData();
        String language = new String(ch, start, length);
        if("en".equals(language)) cabPackagesData.setEnglishProperties(true);
        return this;
    }
}
