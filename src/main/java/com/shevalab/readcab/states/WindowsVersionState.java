package com.shevalab.readcab.states;


import com.shevalab.readcab.RequisiteType;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class WindowsVersionState extends CabPackagesBaseState {
    public WindowsVersionState(String name) {
        super(name);
    }

    public BaseState startElement(Attributes attributes) {
        getCabPackagesData().getCurrentRequisite()
                .setType(RequisiteType.OS);
        return this;
    }

}
