package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Requisite;
import com.shevalab.utils.xml.BaseState;

import java.util.List;

public class UpdateInfoState extends CabPackagesBaseState {

    public UpdateInfoState(String name) {
        super(name);
    }

    @Override
    public BaseState endElement() {
        CabPackagesData cabPackagesData = getCabPackagesData();
        Requisite currentRequisite = cabPackagesData.getCurrentRequisite();
        if (currentRequisite != null) {
            // update category requisites cache
            // category info is cached by a UUID
            String updateId = cabPackagesData.getCurrentUpdate().getUpdateId();
            Requisite cachedRequisite = cabPackagesData.getOrCreateRequisite(updateId);
            cachedRequisite.setType(currentRequisite.getType())
                    .setContent(currentRequisite.getContent());
            List<Requisite> dependencies = currentRequisite.getDependencies();
            if(!(dependencies == null || dependencies.isEmpty())) {
                cachedRequisite.setDependencies(dependencies);
            }
            cabPackagesData.setCurrentRequisite(null);
        }
        return this;
    }
}
