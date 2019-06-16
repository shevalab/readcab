package com.example.readcab.states;

import com.example.readcab.CabPackagesData;
import com.example.readcab.Requisite;
import com.example.readcab.RequisiteType;
import com.example.readcab.UpdateWithRequisitesDto;
import com.shevalab.utils.xml.BaseState;
import org.apache.commons.lang.time.DateUtils;
import org.xml.sax.Attributes;

import java.util.Date;

import static com.example.readcab.RequisiteType.*;

public class UpdateState extends BaseState {

    private boolean applicable;

    public UpdateState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        applicable = false;
        // CreationDate="2019-05-14T17:02:06Z" DefaultLanguage="en" UpdateId="e34205a2-3739-4b7c-b792-22bc71890ca9" RevisionNumber="201" RevisionId="29010705" IsLeaf="true" IsBundle="true"
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        UpdateWithRequisitesDto update = new UpdateWithRequisitesDto();
        try {
            String updateId = attributes.getValue("UpdateId");
            Boolean isBundle = Boolean.valueOf(attributes.getValue("IsBundle"));
            String deploymentAction = attributes.getValue("DeploymentAction");
            String revisionId = attributes.getValue("RevisionId");
            if(isBundle || "Bundle".equals(deploymentAction)) {
                applicable = true;
                update.setUpdateId(updateId);
                Date creationDate = DateUtils.parseDate(attributes.getValue("CreationDate").replace("Z", "+0000"),
                        new String[]{"yyyy-MM-dd'T'HH:mm:ssZ"});
                update.setCreationTime(creationDate);
            }
            RequisiteType requisiteType = applicable ? (isBundle ? BUNDLE : BUNDLED) : NONE;
            switch (requisiteType) {
                case BUNDLE:
                    cabPackagesData.getUpdateRequisiteMap().put(revisionId, new Requisite().setType(requisiteType).setContent(updateId));
                    break;
                case NONE:
                    cabPackagesData.getUpdateRequisiteMap().put(updateId, new Requisite().setType(requisiteType).setContent(revisionId));
                    break;
                default:
            }

        } catch (Exception e) {
            throw new IllegalStateException("Cannot parse XML data", e);
        }


        cabPackagesData.setCurrentUpdate(update);
        return this;
    }

    @Override
    public BaseState endElement() {
        // move the current update to the result list
        if(applicable) {
            CabPackagesData cabPackagesData = (CabPackagesData) getData();
            cabPackagesData.getUpdates().add(cabPackagesData.getCurrentUpdate());
            cabPackagesData.setCurrentUpdate(null);
        }
        return this;
    }
}
