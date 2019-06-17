package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

public class PropertiesTitleState extends BaseState {
    public PropertiesTitleState(String name) {
        super(name);
    }

    @Override
    public BaseState processChars(char[] ch, int start, int length) {
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        if(cabPackagesData.isEnglishProperties()) {
            String title = new String(ch, start, length);
            cabPackagesData.getCurrentUpdate().setTitle(title);
        }
        return this;
    }
}
