package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.readcab.UpdateSeverity;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class UpdatePropertiesState extends CabPackagesBaseState {
    public UpdatePropertiesState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {

        String updateType = attributes.getValue("UpdateType");
//        System.out.println("Update type: " + updateType);
        if("Software".equals(updateType)) {
            String severity = attributes.getValue("MsrcSeverity");
            String legacyName = attributes.getValue("LegacyName");
            if (!(severity == null || legacyName == null)) {
                getCabPackagesData().getCurrentUpdate().setSeverity(UpdateSeverity.valueOf(severity.toUpperCase()))
                        .setLegacyName(legacyName);
            }
        } else if("Detectoid".equals(updateType)) {
//            System.out.println(attributes.getValue("DetectoidType"));
        } else if("Category".equals(updateType)) {
            getCabPackagesData().setCurrentCategoryInfo(new Requisite());
        }
        return this;
    }
}
