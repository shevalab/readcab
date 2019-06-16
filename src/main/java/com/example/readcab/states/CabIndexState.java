package com.example.readcab.states;

import com.example.readcab.RevisionToFile;
import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;


public class CabIndexState extends BaseState {
    public CabIndexState(String tag) {
        super(tag);
    }

    @Override
    public BaseState startElement(Attributes attributes) {
        RevisionToFile revisionMap = (RevisionToFile)getData();
        String fileName = null;
        Long fromRevision = null;
        for(int i = 0; i < attributes.getLength(); i++) {
            String qName = attributes.getQName(i).toLowerCase();
            String value = attributes.getValue(i).toLowerCase();
            if("name".equals(qName)) {
                fileName = value;
            } else if("rangestart".equals(qName)) {
                fromRevision = Long.valueOf(value);
            }
        }
        if(!(fileName == null || fromRevision == null)) {
            revisionMap.getRevisions().add(fromRevision);
            revisionMap.getFiles().add(fileName);
        }
        return this;
    }

}
