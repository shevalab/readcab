package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;

public class UpdateInfoState extends CabPackagesBaseState {

    public UpdateInfoState(String name) {
        super(name);
    }

    @Override
    public BaseState endElement() {
        CabPackagesData cabPackagesData = getCabPackagesData();
        Requisite categoryInfo = cabPackagesData.getCurrentCategoryInfo();
        if(categoryInfo != null) {
            // update category requisites cache
            // category info is cached by a UUID
            String updateId = cabPackagesData.getCurrentUpdate().getUpdateId();
            Requisite categoryRequisite = cabPackagesData.getOrCreateRequisite(updateId);
            categoryRequisite.setType(categoryInfo.getType()).setContent(categoryInfo.getContent());
            cabPackagesData.setCurrentCategoryInfo(null);
        }
        return super.endElement();
    }
}
