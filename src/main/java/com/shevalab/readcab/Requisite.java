package com.shevalab.readcab;

public class Requisite {
    private RequisiteType type = RequisiteType.NONE;
    private String content;

    public RequisiteType getType() {
        return type;
    }

    public Requisite setType(RequisiteType type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Requisite setContent(String content) {
        this.content = content;
        return this;
    }
}
