package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.readcab.RequisiteType;
import com.shevalab.readcab.UpdateSeverity;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

import static com.shevalab.readcab.RequisiteType.*;

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
        } else {
            Requisite requisite = new Requisite();
            if("Detectoid".equals(updateType)) {
                String detectoidType =  attributes.getValue("DetectoidType");
                if("Architecture".equals(detectoidType)) {
                    requisite.setType(ARCH);
                }
            }
            getCabPackagesData().setCurrentRequisite(requisite);
        }
        return this;
    }
    @Override
    public BaseState endElement() {
        CabPackagesData cabPackagesData = getCabPackagesData();
        Requisite currentRequisite = cabPackagesData.getCurrentRequisite();
        if (currentRequisite != null) {
            RequisiteType type = currentRequisite.getType();
            // update category requisites cache
            // category info is cached by a UUID
            String updateId = cabPackagesData.getCurrentUpdate().getUpdateId();
            Requisite categoryRequisite = cabPackagesData.getOrCreateRequisite(updateId);
            categoryRequisite.setType(type).setContent(currentRequisite.getContent());
            cabPackagesData.setCurrentRequisite(null);
        }
        return this;
    }
}
