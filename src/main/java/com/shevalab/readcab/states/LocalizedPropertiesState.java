package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

public class LocalizedPropertiesState extends CabPackagesBaseState {
    public LocalizedPropertiesState(String name) {
        super(name);
    }

    @Override
    public BaseState endElement() {
        CabPackagesData cabPackagesData = getCabPackagesData();
        cabPackagesData.setEnglishProperties(false);
        return this;
    }
}
