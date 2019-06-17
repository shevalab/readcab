package com.example.readcab;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UpdateWithRequisitesDto extends WsusUpdateDto {
    private List<Requisite> preRequisites = new LinkedList<>();
    private List<Requisite> bundledByRequisites = new LinkedList<>();
    private List<Requisite> supersededByRequisites = new LinkedList<>();
    private List<String> payloadFiles = null;
    private Set<String> languages = new TreeSet<>();

    public List<Requisite> getPreRequisites() {
        return preRequisites;
    }

    public List<Requisite> getBundledByRequisites() {
        return bundledByRequisites;
    }

    public List<Requisite> getSupersededByRequisites() {
        return supersededByRequisites;
    }

    public UpdateWithRequisitesDto addPayloadFile(String file) {
        if(payloadFiles == null) payloadFiles = new LinkedList<>();
        payloadFiles.add(file);
        return this;
    }

    public List<String> getPayloadFiles() {
        return payloadFiles;
    }

    public Set<String> getLanguages() {
        return languages;
    }
}
