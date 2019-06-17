package com.example.readcab.states;

import com.example.readcab.CabPackagesData;
import com.example.readcab.Requisite;
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
