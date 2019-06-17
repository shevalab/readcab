package com.shevalab.readcab.states;

import com.shevalab.readcab.CabPackagesData;
import com.shevalab.readcab.PayloadFile;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class FileLocationState extends BaseState {
    public FileLocationState(String name) {
        super(name);
    }



    @Override
    public BaseState startElement(Attributes attributes) {
    /*
    <FileLocation Id="zs0CQi9xMy/tvVU8nu2J5HSGOP4=" Url="http://download.windowsupdate.com/msdownload/update/software/secu/2010/01/windowsxp-kb975713-x86-ptg_cecd02422f71332fedbd553c9eed89e4748638fe.exe"/>
     */
        String id = attributes.getValue("Id");
        String url = attributes.getValue("Url");

        CabPackagesData cabPackagesData = (CabPackagesData) getData();
        cabPackagesData.getPayloadFiles().add(new PayloadFile().setDigest(id).setLocation(url));
        return this;
    }
}
