package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class CategoryInformationState extends CabPackagesBaseState {
    public CategoryInformationState(String name) {
        super(name);
    }

    public BaseState startElement(Attributes attributes) {
    /*
 <cat:CategoryInformation CategoryType="ProductFamily" ProhibitsSubcategories="false" ProhibitsUpdates="true" DisplayOrder="2" />
    */
        String categoryType = attributes.getValue("CategoryType");

        getCabPackagesData().getCurrentCategoryInfo()
                .setType(CategoryState.getRequisiteType(categoryType));
        return this;
    }
}
