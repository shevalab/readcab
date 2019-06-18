package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.Impact;
import com.shevalab.readcab.RebootBehavior;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public abstract class BehaviorState extends CabPackagesBaseState {
    public BehaviorState(String elementName) {
        super(elementName);
    }

    @Override
    public BaseState startElement(Attributes attributes) {

        String rebootBehavior = attributes.getValue("RebootBehavior");
        String impact = attributes.getValue("Impact");
        CabPackagesData cabPackagesData = getCabPackagesData();
        setRebootBehavior(cabPackagesData, convertRebootBehavior(rebootBehavior), convertImpact(impact));
        return this;
    }

    private Impact convertImpact(String impact) {
        if(impact == null) return null;
        switch (impact) {
            case "Minor": return Impact.MINOR;
            case "Normal": return Impact.NORMAL;
            case "RequiresExclusiveHandling": return Impact.EXCLUSIVE_HANDLING;
        }
        return Impact.NORMAL;
    }

    private RebootBehavior convertRebootBehavior(String rebootBehavior) {
        if(rebootBehavior == null) return null;
        switch (rebootBehavior) {
            case "AlwaysRequiresReboot": return RebootBehavior.ALWAYS_REBOOT;
            case "CanRequestReboot": return RebootBehavior.CAN_REBOOT;
            case "NeverReboots": return RebootBehavior.NEVER_REBOOT;
        }
        return RebootBehavior.CAN_REBOOT;
    }

    protected abstract void setRebootBehavior(CabPackagesData cabPackagesData, RebootBehavior behavior, Impact impact);
}
