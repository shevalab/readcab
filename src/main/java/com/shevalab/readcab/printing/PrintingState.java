package com.shevalab.readcab.printing;

import com.shevalab.utils.xml.BaseState;
import org.xml.sax.Attributes;

public class PrintingState extends BaseState {

    public PrintingState() {}

    public PrintingState(String elementName) {
        super(elementName);
    }


    @Override
    public BaseState startElement(Attributes attributes) {
        IndentData indentData = (IndentData) getData();
        String indent = indentData.getIndent() + "  ";
        indentData.setIndent(indent);
        System.out.println(indent + getElementName());
        for(int i = 0; i < attributes.getLength(); i++) {
            System.out.println(indent + "    " + attributes.getQName(i) + " : " + attributes.getValue(i));
        }
        return super.startElement(attributes);
    }

    @Override
    public BaseState endElement() {
        IndentData indentData = (IndentData)getData();
        String indent = indentData.getIndent();
        indentData.setIndent(indent.substring(0, indent.length()-2));
        return this;
    }

}
