package com.example.readcab;

import static com.example.readcab.RequisiteType.NONE;

public class Requisite {
    RequisiteType type = NONE;
    String content;

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
