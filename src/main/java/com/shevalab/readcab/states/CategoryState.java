package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.readcab.RequisiteType;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class CategoryState extends BaseState {
    public CategoryState(String name) {
        super(name);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        /*
        <Category Type="Company" Id="56309036-4c77-4dd9-951a-99ee9c246a94"/>
        <Category Type="Product" Id="a3c2375d-0c8a-42f9-bce0-28333e198407"/>
        <Category Type="ProductFamily" Id="6964aab4-c5b5-43bd-a17d-ffb4346a8e1d"/>
        <Category Type="UpdateClassification" Id="0fa1201d-4330-4fa8-8ae9-b877473b6441"/>

         */
        String type = attributes.getValue("Type");
        String id = attributes.getValue("Id");
        CabPackagesData cabPackagesData = (CabPackagesData)getData();
        RequisiteType requisiteType = RequisiteType.NONE;
        switch(type) {
            case "Company": requisiteType = RequisiteType.COMPANY;
                break;
            case "Product": requisiteType = RequisiteType.PRODUCT;
                break;
            case "ProductFamily": requisiteType = RequisiteType.PRODUCT_FAMILY;
                break;
            case "UpdateClassification": requisiteType = RequisiteType.CLASSIFICATION;
                break;
        }
        Requisite requisite = cabPackagesData.getOrCreateRequisite(id, requisiteType);
        cabPackagesData.getCurrentUpdate().getPreRequisites().add(requisite);
        return this;
    }
}
