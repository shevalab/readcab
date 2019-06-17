package com.example.readcab.states;

import com.example.readcab.CabPackagesData;
import com.example.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class PrerequisiteState extends BaseState {
    public PrerequisiteState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /* <UpdateId Id="3e0afb10-a9fb-4c16-a60e-5790c3803437"/> */

        String id = attributes.getValue("Id");
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        Requisite requisite = cabPackagesData.getOrCreateRequisite(id);
        cabPackagesData.getCurrentUpdate().getPreRequisites().add(requisite);
        return this;
    }
}
