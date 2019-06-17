package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class SupersededByState extends BaseState {
    public SupersededByState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /* <Revision Id="29010703"/> */

        String id = attributes.getValue("Id");
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        Requisite requisite = cabPackagesData.getOrCreateRequisite(id);
        cabPackagesData.getCurrentUpdate().getSupersededByRequisites().add(requisite);
        return this;
    }
}
