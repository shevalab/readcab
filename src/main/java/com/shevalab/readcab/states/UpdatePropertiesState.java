package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.UpdateSeverity;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class UpdatePropertiesState extends BaseState {
    public UpdatePropertiesState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {

        String severity = attributes.getValue("MsrcSeverity");
        String legacyName = attributes.getValue("LegacyName");
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        if(!(severity == null || legacyName == null)) {
            cabPackagesData.getCurrentUpdate().setSeverity(UpdateSeverity.valueOf(severity.toUpperCase()))
                    .setLegacyName(legacyName);
        }
        return this;
    }
}
