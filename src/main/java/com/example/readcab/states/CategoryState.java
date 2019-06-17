package com.example.readcab.states;

import com.example.readcab.CabPackagesData;
import com.example.readcab.Requisite;
import com.example.readcab.RequisiteType;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

import static com.example.readcab.RequisiteType.*;

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
        RequisiteType requisiteType = NONE;
        switch(type) {
            case "Company": requisiteType = COMPANY;
                break;
            case "Product": requisiteType = PRODUCT;
                break;
            case "ProductFamily": requisiteType = PRODUCT_FAMILY;
                break;
            case "UpdateClassification": requisiteType = CLASSIFICATION;
                break;
        }
        Requisite requisite = cabPackagesData.getOrCreateRequisite(id, requisiteType);
        cabPackagesData.getCurrentUpdate().getPreRequisites().add(requisite);
        return this;
    }
}
