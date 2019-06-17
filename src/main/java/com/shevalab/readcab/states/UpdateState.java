package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.CabParserSaxHelper;
import com.shevalab.readcab.RequisiteType;
import com.shevalab.readcab.UpdateWithRequisitesDto;
import com.shevalab.utils.xml.BaseState;
import org.apache.commons.lang.time.DateUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

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
        cabPackagesData.setCurrentUpdate(update);
        try {
            String updateId = attributes.getValue("UpdateId");
            Boolean isBundle = Boolean.valueOf(attributes.getValue("IsBundle"));
            String deploymentAction = attributes.getValue("DeploymentAction");
            String revisionId = attributes.getValue("RevisionId");
            String defaultLanguage = attributes.getValue("DefaultLanguage");

//            System.out.println(updateId + " : " + revisionId);
            if(isBundle || "Bundle".equals(deploymentAction)) {
                applicable = true;
                update.setUpdateId(updateId);
                Date creationDate = DateUtils.parseDate(attributes.getValue("CreationDate").replace("Z", "+0000"),
                        new String[] {"yyyy-MM-dd'T'HH:mm:ssZ"});
                update.setCreationTime(creationDate);
                update.getLanguages().add(defaultLanguage);
            }
            parseSProperties(cabPackagesData, revisionId);
            RequisiteType requisiteType = applicable ? (isBundle ? RequisiteType.BUNDLE : RequisiteType.NONE) : RequisiteType.NONE;
            switch (requisiteType) {
                case BUNDLE:
                    cabPackagesData.getOrCreateRequisite(revisionId).setType(requisiteType).setContent(updateId);
                    break;
                case NONE:
                    cabPackagesData.getOrCreateRequisite(updateId).setType(requisiteType).setContent(revisionId);
                    break;
                default:
            }

        } catch (Exception e) {
            throw new IllegalStateException("Cannot parse XML data", e);
        }
        return this;
    }

    private void parseSProperties(CabPackagesData cabPackagesData, String revisionId) throws ParserConfigurationException, SAXException, IOException {
        CabParserSaxHelper parser = cabPackagesData.getParserHelper();
        BaseState state = new BaseState()
                .child(new BaseState("upd:Update")
                        .child(new UpdatePropertiesState("upd:Properties")
                                .child(new KbArticleState("upd:KBArticleID"))
                                .child(new InstallationBehaviorState("upd:InstallationBehavior"))
                                .child(new UninstallationBehaviorState("upd:UninstallationBehavior"))
                                .setAllowMissingChild(true)
                        )
                        .child(new BaseState("upd:LocalizedPropertiesCollection")
                                .child(new LocalizedPropertiesState("upd:LocalizedProperties")
                                        .child(new PropertiesLanguageState("upd:Language"))
                                        .child(new PropertiesTitleState("upd:Title"))
                                        .setAllowMissingChild(true)
                                )
                                .setAllowMissingChild(true)
                        )
                        .setAllowMissingChild(true)
                ).setData(cabPackagesData);
        parser.parseFile(new File(CabParserSaxHelper.getBasePath(), buildSPath(parser.getIndex().getFile(revisionId), revisionId)), state);
    }

    private String buildSPath(String folder, String revisionId) {
        return folder.substring(0, folder.length() - 4) + "/s/" + revisionId;
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
