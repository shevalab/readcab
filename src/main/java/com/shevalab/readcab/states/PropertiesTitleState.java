package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;

public class PropertiesTitleState extends CabPackagesBaseState {
    public PropertiesTitleState(String name) {
        super(name);
    }

    @Override
    public BaseState processChars(char[] ch, int start, int length) {
        CabPackagesData cabPackagesData = getCabPackagesData();
        if(cabPackagesData.isEnglishProperties()) {
            String title = new String(ch, start, length);
            Requisite categoryInfo = cabPackagesData.getCurrentCategoryInfo();
            if(categoryInfo != null) {
                categoryInfo.setContent(title);
            } else{
                cabPackagesData.getCurrentUpdate().setTitle(title);
            }
        }
        return this;
    }

}
