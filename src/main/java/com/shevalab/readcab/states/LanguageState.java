package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class LanguageState extends CabPackagesBaseState {
    public LanguageState(String name) {
        super(name);
    }


    @Override
    public BaseState startElement(Attributes attributes) {
        /* <Language Name="en"/> */

        String language = attributes.getValue("Name");
        CabPackagesData cabPackagesData = getCabPackagesData();
        cabPackagesData.getCurrentUpdate().getLanguages().add(language);
        return this;
    }
}
