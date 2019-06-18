package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;

public class CabPackagesBaseState extends BaseState {
    public CabPackagesBaseState(String elementName) {
        super(elementName);
    }

    protected CabPackagesData getCabPackagesData() {
        return (CabPackagesData)getData();
    }
}
