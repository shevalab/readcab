package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

public class LocalizedPropertiesState extends BaseState {
    public LocalizedPropertiesState(String name) {
        super(name);
    }

    @Override
    public BaseState endElement() {
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        cabPackagesData.setEnglishProperties(false);
        return this;
    }
}
