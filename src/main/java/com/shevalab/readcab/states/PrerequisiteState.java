package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

public class PrerequisiteState extends CabPackagesBaseState {
    public PrerequisiteState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /* <UpdateId Id="3e0afb10-a9fb-4c16-a60e-5790c3803437"/> */

        String id = attributes.getValue("Id");
        CabPackagesData cabPackagesData = getCabPackagesData();
        Requisite requisite = cabPackagesData.getOrCreateRequisite(id);
        if(StringUtils.isEmpty(requisite.getContent())) { // content might be already populated
            requisite.setContent(id);
        }
        cabPackagesData.getCurrentUpdate().getPreRequisites().add(requisite);
        return this;
    }
}
