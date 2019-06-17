package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Impact;
import com.shevalab.readcab.RebootBehavior;

public class UninstallationBehaviorState extends BehaviorState {
    public UninstallationBehaviorState(String name) {
        super(name);
    }

    @Override
    protected void setRebootBehavior(CabPackagesData cabPackagesData, RebootBehavior behavior, Impact impact) {
        cabPackagesData.getCurrentUpdate()
                .setRemovalRebootBehavior(behavior)
                .setRemovalImpact(impact);
    }
}
