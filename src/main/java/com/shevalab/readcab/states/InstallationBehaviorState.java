package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Impact;
import com.shevalab.readcab.RebootBehavior;


public class InstallationBehaviorState extends BehaviorState {
    public InstallationBehaviorState(String name) {
        super(name);
    }

    @Override
    protected void setRebootBehavior(CabPackagesData cabPackagesData, RebootBehavior behavior, Impact impact) {
        cabPackagesData.getCurrentUpdate()
                .setInstallationRebootBehavior(behavior)
                .setInstallationImpact(impact);
    }
}
