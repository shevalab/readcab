package com.shevalab.readcab.states;


import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

public class PropertiesUpdateIdentityState extends CabPackagesBaseState {
    public PropertiesUpdateIdentityState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /* <UpdateId Id="3e0afb10-a9fb-4c16-a60e-5790c3803437"/> */

        String updateId = attributes.getValue("UpdateID");
        CabPackagesData cabPackagesData = getCabPackagesData();
        Requisite requisite = cabPackagesData.getOrCreateRequisite(updateId);
        if(StringUtils.isEmpty(requisite.getContent())) { // content might be already populated
            requisite.setContent(updateId);
        }
        Requisite currentRequisite = cabPackagesData.getCurrentRequisite();
        if(currentRequisite != null) {
            currentRequisite.getDependencies().add(requisite);
        }
        return this;
    }
}
