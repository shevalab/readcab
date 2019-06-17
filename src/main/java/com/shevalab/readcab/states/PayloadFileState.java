package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class PayloadFileState extends BaseState {
    public PayloadFileState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /* <File Id="Z1lhqr36JDqP4+jJ2wku9Xe0UN8="/> */

        String id = attributes.getValue("Id");
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        cabPackagesData.getCurrentUpdate().addPayloadFile(id);
        return this;
    }
}
